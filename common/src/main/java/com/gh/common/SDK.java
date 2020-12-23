package com.gh.common;

import com.gh.common.api.impl.DateAPIImpl;
import com.gh.common.api.service.DateAPI;

public class SDK {

    public static DateAPI getDateAPI() {
        return new DateAPIImpl();
    }

}
