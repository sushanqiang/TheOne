/*
 * Copyright 2015 Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pioneers.recyclerviewitemanimation.http;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;


/**
 * 实现请求接口
 */
public class HttpResponseListener<T> implements OnResponseListener<T> {
    private Request<?> mRequest;
    private ProgressDialog shapeLoadingDialog;//显示加载
    private Context context;
    /**
     * 结果回调.
     */
    private HttpListener<T> callback;
    /**
     * 是否显示dialog.
     */
    private boolean isLoading;
    private boolean canCancel;
    /**
     * @param context      context用来实例化dialog.
     * @param request      请求对象.
     * @param httpCallback 回调对象.
     * @param canCancel    是否允许用户取消请求.
     * @param isLoading    是否显示dialog.
     */
    public HttpResponseListener(Context context, Request<?> request, HttpListener<T> httpCallback, boolean canCancel, boolean isLoading) {
        this.context = context;
        this.mRequest = request;
        this.callback = httpCallback;
        this.isLoading = isLoading;
        this.canCancel = canCancel;
    }
    public HttpResponseListener( Request<?> request, HttpListener<T> httpCallback, boolean canCancel, boolean isLoading) {
        this.mRequest = request;
        this.callback = httpCallback;
        this.isLoading = isLoading;
        this.canCancel = canCancel;
    }
    /**
     * 开始请求,这里显示一个dialog.
     */
    @Override
    public void onStart(int what) {
        if (shapeLoadingDialog == null && isLoading) {
            shapeLoadingDialog = new ProgressDialog(context);
            shapeLoadingDialog.show();
        }
    }
    /**
     * 结束请求,关闭dialog.
     */
    @Override
    public void onFinish(int what) {
        if (callback != null) {
            callback.onFinish(what);
        }
        if (isLoading) {
            shapeLoadingDialog.dismiss();
        }
        if (canCancel&&mRequest!=null){
            mRequest.cancel();
        }
    }
    /**
     * 成功回调.
     */
    @Override
    public void onSucceed(int what, Response<T> response) {
        int responseCode = response.getHeaders().getResponseCode();
        if (responseCode > 400 && context != null) {
//            if (responseCode == 405) {// 405表示服务器不支持这种请求方法，比如GET、POST、TRACE中的TRACE就很少有服务器支持。
//                BaseApp.getInstance().showToast(responseCode+"请求方法不允许");
//            } else {// 但是其它400+的响应码服务器一般会有流输出。
//                BaseApp.getInstance().showToast(responseCode+"请求失败");
//            }
            if (isLoading&&shapeLoadingDialog!=null) {
                shapeLoadingDialog.dismiss();
            }
        }
        if (callback != null) {
            callback.onSucceed(what, response);
        }
        if (canCancel&&mRequest!=null){
            mRequest.cancel();
        }
    }
    /**
     * 失败回调.
     */
    @Override
    public void onFailed(int what, Response<T> response) {
        Exception exception=response.getException();
        Log.e("错误："+response.responseCode(),"exception======"+exception+ "  "+exception.getMessage());
        if (callback != null)
            callback.onFailed(what, response.request().url(), response.getTag(), exception, response.responseCode(), response.getNetworkMillis());
        if (canCancel&&mRequest!=null){
            mRequest.cancel();
        }
    }


}
