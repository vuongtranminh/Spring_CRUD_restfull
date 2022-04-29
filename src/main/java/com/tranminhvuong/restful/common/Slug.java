package com.tranminhvuong.restful.common;

import com.github.slugify.Slugify;

public class Slug {

    public static String setSlugify(String str) {
        return new Slugify().slugify(str);
    }

}
