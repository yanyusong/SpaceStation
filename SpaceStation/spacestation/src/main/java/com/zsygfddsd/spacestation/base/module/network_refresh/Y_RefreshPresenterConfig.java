package com.zsygfddsd.spacestation.base.module.network_refresh;

import com.zsygfddsd.spacestation.common.DefaultErrorHandler;
import com.zsygfddsd.spacestation.common.ErrorHandler;

/**
 * Created by mac on 16/7/27.
 */
public class Y_RefreshPresenterConfig {

    public ErrorHandler errorHandler = new DefaultErrorHandler();

    private Y_RefreshPresenterConfig() {
    }

    public static class Builder {

        ErrorHandler errorHandler = new DefaultErrorHandler();

        public Builder setErrorHandler(ErrorHandler mErrorHandler) {
            errorHandler = mErrorHandler;
            return this;
        }

        void applyConfig(Y_RefreshPresenterConfig config) {
            config.errorHandler = this.errorHandler;
        }

        public Y_RefreshPresenterConfig create() {
            Y_RefreshPresenterConfig y_pagePresenterConfig = new Y_RefreshPresenterConfig();
            applyConfig(y_pagePresenterConfig);
            return y_pagePresenterConfig;
        }


    }
}
