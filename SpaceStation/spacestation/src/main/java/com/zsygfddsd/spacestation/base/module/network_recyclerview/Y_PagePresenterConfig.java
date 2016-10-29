package com.zsygfddsd.spacestation.base.module.network_recyclerview;

/**
 * Created by mac on 16/7/27.
 */
public class Y_PagePresenterConfig {

    int FirstPageIndex = 1;

    int PageSize = 10;

    boolean isInitDialogShow = false;
    boolean isRefreshDialogShow = true;
    boolean isLoadMoreDialogShow = true;

    private Y_PagePresenterConfig() {
    }

    public static class Builder {

        int FirstPageIndex = 1;

        int PageSize = 10;

        boolean isInitDialogShow = false;
        boolean isRefreshDialogShow = true;
        boolean isLoadMoreDialogShow = true;


        public Builder setFirstPageIndex(int firstPageIndex) {
            FirstPageIndex = firstPageIndex;
            return this;
        }

        public Builder setPageSize(int pageSize) {
            PageSize = pageSize;
            return this;
        }

        public Builder setInitDialogShow(boolean initDialogShow) {
            isInitDialogShow = initDialogShow;
            return this;
        }

        public Builder setRefreshDialogShow(boolean refreshDialogShow) {
            isRefreshDialogShow = refreshDialogShow;
            return this;
        }

        public Builder setLoadMoreDialogShow(boolean loadMoreDialogShow) {
            isLoadMoreDialogShow = loadMoreDialogShow;
            return this;
        }

        void applyConfig(Y_PagePresenterConfig config) {
            config.FirstPageIndex = this.FirstPageIndex;
            config.PageSize = this.PageSize;
            config.isInitDialogShow = this.isInitDialogShow;
            config.isRefreshDialogShow = this.isRefreshDialogShow;
            config.isLoadMoreDialogShow = this.isLoadMoreDialogShow;
        }

        public Y_PagePresenterConfig create() {
            Y_PagePresenterConfig y_pagePresenterConfig = new Y_PagePresenterConfig();
            applyConfig(y_pagePresenterConfig);
            return y_pagePresenterConfig;
        }


    }
}
