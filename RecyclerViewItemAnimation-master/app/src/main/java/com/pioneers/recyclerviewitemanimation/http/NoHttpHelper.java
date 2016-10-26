package com.pioneers.recyclerviewitemanimation.http;


import android.content.Context;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.CacheMode;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;
import java.util.Map;
/**
 * Created by sushanqiang on 2016/6/20.
 */
public class NoHttpHelper {
    private static NoHttpHelper helper;
    private Request<String> request = null;

    private NoHttpHelper() {
        /**
         * 设置请求模式
         * 每次请求网络，没有就请求缓存
         * 非标准Http协议，改变缓存模式为REQUEST_NETWORK_FAILED_READ_CACHE
         * 默认请求时间为8秒
         */
        if (request != null) {
            request.setCacheMode(CacheMode.DEFAULT);
            request.setConnectTimeout(10 * 1000);
            request.setReadTimeout(10*1000);
        }
    }

    public static NoHttpHelper getHelper() {
        if (helper == null) {
            synchronized (NoHttpHelper.class) {
                if (helper == null) {
                    helper = new NoHttpHelper();
                }
            }
        }
        return helper;
    }
    /**
     * get请求,不加上下文，加了设置请求时间
     */
    public void getString(int time, String url, boolean canCancel, boolean isLoading, final CallBack callBack) {
        request = NoHttp.createStringRequest(url);
        request.setCacheMode(CacheMode.ONLY_REQUEST_NETWORK);
        request.setConnectTimeout(time);
        request.setReadTimeout(time);
        CallServer.getRequestInstance().add(0, request, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                if (callBack != null) {
                    callBack.onSucceed(what, response);
                }
            }
            @Override
            public void onFinish(int what) {
                if (callBack != null) {
                    callBack.onFinish(what);
                }
            }
            @Override
            public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                if (callBack != null) {
                    callBack.onFailed(what, url, tag, exception, responseCode, networkMillis);
                }
            }
        }, canCancel, isLoading);
    }
    /**
     * get请求,加key网络缓存
     */
    public void getString(String key,Context context, String url, boolean canCancel, boolean isLoading, final CallBack callBack) {
        request = NoHttp.createStringRequest(url);
        if (key!=null){
            request.setCacheKey(key);
            request.setCacheMode(CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE);
        }else {
            request.setCacheMode(CacheMode.ONLY_REQUEST_NETWORK);
        }
        request.setConnectTimeout(10 * 1000);
        request.setReadTimeout(10*1000);
        CallServer.getRequestInstance().add(context,1, request, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                if (callBack != null) {
                    callBack.onSucceed(what, response);
                }
            }

            @Override
            public void onFinish(int what) {
                if (callBack != null) {
                    callBack.onFinish(what);
                }
            }
            @Override
            public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                if (callBack != null) {
                    callBack.onFailed(what, url, tag, exception, responseCode, networkMillis);
                }
            }
        }, canCancel, isLoading);
    }
    /**
     * get请求
     */
    public void getString(Context context, String url, boolean canCancel, boolean isLoading, final CallBack callBack) {
        request = NoHttp.createStringRequest(url);
        request.setCacheMode(CacheMode.ONLY_REQUEST_NETWORK);
        request.setConnectTimeout(10 * 1000);
        request.setReadTimeout(10*1000);
        CallServer.getRequestInstance().add(context,2, request, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                if (callBack != null) {
                    callBack.onSucceed(what, response);
                }
            }

            @Override
            public void onFinish(int what) {
                if (callBack != null) {
                    callBack.onFinish(what);
                }
            }
            @Override
            public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                if (callBack != null) {
                    callBack.onFailed(what, url, tag, exception, responseCode, networkMillis);
                }
            }
        }, canCancel, isLoading);
    }
    /**
     * post请求
     */
    public void postString(Context context, String url, Map<String, String> params, final boolean canCancel, boolean isLoading, final CallBack callBack) {
        request = NoHttp.createStringRequest(url, RequestMethod.POST);
        request.setCacheMode(CacheMode.ONLY_REQUEST_NETWORK);
        request.setConnectTimeout(10 * 1000);
        request.setReadTimeout(10*1000);
        request.set(params);
        CallServer.getRequestInstance().add(context, 3, request, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                if (callBack != null) {
                    callBack.onSucceed(what, response);
                }
            }
            @Override
            public void onFinish(int what) {
                if (callBack != null) {
                    callBack.onFinish(what);
                }
            }
            @Override
            public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                if (callBack != null) {
                    callBack.onFailed(what, url, tag, exception, responseCode, networkMillis);
                }
            }
        }, canCancel, isLoading);
    }
    /**
     * post请求,不需要上下文
     */
    public void postString( String url, Map<String, String> params, final boolean canCancel, boolean isLoading, final CallBack callBack) {
        request = NoHttp.createStringRequest(url, RequestMethod.POST);
        request.setCacheMode(CacheMode.ONLY_REQUEST_NETWORK);
        request.setConnectTimeout(10 * 1000);
        request.setReadTimeout(10*1000);
        request.set(params);
        CallServer.getRequestInstance().add( 4, request, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                if (callBack != null) {
                    callBack.onSucceed(what, response);
                }
            }
            @Override
            public void onFinish(int what) {
                if (callBack != null) {
                    callBack.onFinish(what);
                }
            }

            @Override
            public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                if (callBack != null) {
                    callBack.onFailed(what, url, tag, exception, responseCode, networkMillis);
                }
            }
        }, canCancel, isLoading);
    }

    public void onDestroy() {
        if (request != null) {
            request.cancel();
            CallServer.getRequestInstance().stopAll();
            CallServer.getRequestInstance().cancelAll();
            helper=null;
            request = null;
        }
    }

    public interface CallBack {
        void onSucceed(int what, Response<String> response);

        void onFinish(int what);

        void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis);
    }
}