package com.cathy.cathyblog.common.util;

import com.cathy.cathyblog.common.consts.SiteConfig;
import org.apache.commons.codec.digest.DigestUtils;

public class ThumbnailUtil {
    /**
     * Gets the Gravatar URL for the specified email with the specified size.
     *
     * @param email the specified email
     * @param size  the specified size
     * @return the Gravatar URL
     */
    public static String getGravatarURL(final String email, final String size) {
        return SiteConfig.GVATAR + DigestUtils.md5Hex(email) + "?s=" + size;
    }

    /**
     * Private constructor.
     */
    private ThumbnailUtil() {
    }
}
