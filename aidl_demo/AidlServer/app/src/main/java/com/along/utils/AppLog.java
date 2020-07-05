/*
 * Copyright (c) 2019. Parrot Faurecia Automotive S.A.S. All rights reserved.
 */

package com.along.utils;

/**
 * log工具类
 */
public class AppLog {

    /**
     * Tag of log.
     */
    private static final String TAG = "AidlServer";

    /**
     * When the object is null, the string "null" is returned.
     */
    private static final String NULL = "<null>";

    /**
     * This is a colon.
     */
    private static final String COLON = ": ";

    /**
     * Constant for support print log.
     */
    private static final boolean FORCE_LOGGING = true;

    /**
     * Constant for support the debug print.
     */
    private static final boolean DEBUG = isLoggable(android.util.Log.DEBUG);

    /**
     * Constant for support the info print.
     */
    private static final boolean INFO = isLoggable(android.util.Log.INFO);

    /**
     * Constant for support the verbose print.
     */
    private static final boolean VERBOSE = isLoggable(android.util.Log.VERBOSE);

    /**
     * Constant for support the warn print.
     */
    private static final boolean WARN = isLoggable(android.util.Log.WARN);

    /**
     * Constant for support the error print.
     */
    private static final boolean ERROR = isLoggable(android.util.Log.ERROR);

    /**
     * Constructor of AppLog.
     */
    private AppLog() {
    }

    /**
     * Determine whether print logs are supported.
     *
     * @param level Method of print log.
     * @return Support print.
     */
    private static boolean isLoggable(int level) {
        return FORCE_LOGGING || android.util.Log.isLoggable(TAG, level);
    }

    /**
     * Change object to class name.
     *
     * @param obj Object of class.
     * @return The name of object.
     */
    private static String getPrefixFromObject(Object obj) {
        return obj == null ? NULL : obj.getClass().getSimpleName();
    }

    /**
     * Send a debug log message.
     *
     * @param prefix The subtag you would like logged.
     * @param msg The message you would like logged.
     */
    public static void debug(String prefix, String msg) {
        if (DEBUG) {
            android.util.Log.d(TAG, prefix + COLON + msg);
        }
    }

    /**
     * Send a debug log message.
     *
     * @param objectPrefix The object name you would like logged.
     * @param msg The message you would like logged.
     */
    public static void debug(Object objectPrefix, String msg) {
        if (DEBUG) {
            android.util.Log.d(TAG, getPrefixFromObject(objectPrefix) + COLON + msg);
        }
    }

    /**
     * Send a info log message.
     *
     * @param prefix The subtag you would like logged.
     * @param msg The message you would like logged.
     */
    public static void info(String prefix, String msg) {
        if (INFO) {
            android.util.Log.i(TAG, prefix + COLON + msg);
        }
    }

    /**
     * Send a info log message.
     *
     * @param objectPrefix The object name you would like logged.
     * @param msg The message you would like logged.
     */
    public static void info(Object objectPrefix, String msg) {
        if (INFO) {
            android.util.Log.i(TAG, getPrefixFromObject(objectPrefix) + COLON + msg);
        }
    }

    /**
     * Send a verbose log message.
     *
     * @param prefix The subtag you would like logged.
     * @param msg The message you would like logged.
     */
    public static void verbose(String prefix, String msg) {
        if (VERBOSE) {
            android.util.Log.v(TAG, prefix + COLON + msg);
        }
    }

    /**
     * Send a verbose log message.
     *
     * @param objectPrefix The object name you would like logged.
     * @param msg The message you would like logged.
     */
    public static void verbose(Object objectPrefix, String msg) {
        if (VERBOSE) {
            android.util.Log.v(TAG, getPrefixFromObject(objectPrefix) + COLON + msg);
        }
    }

    /**
     * Send a warn log message.
     *
     * @param prefix The subtag you would like logged.
     * @param msg The message you would like logged.
     */
    public static void warn(String prefix, String msg) {
        if (WARN) {
            android.util.Log.w(TAG, prefix + COLON + msg);
        }
    }

    /**
     * Send a warn log message.
     *
     * @param objectPrefix The object name you would like logged.
     * @param msg The message you would like logged.
     */
    public static void warn(Object objectPrefix, String msg) {
        if (WARN) {
            android.util.Log.w(TAG, getPrefixFromObject(objectPrefix) + COLON + msg);
        }
    }

    /**
     * Send a error log message.
     *
     * @param prefix The subtag you would like logged.
     * @param msg The message you would like logged.
     */
    public static void error(String prefix, String msg) {
        if (ERROR) {
            android.util.Log.e(TAG, prefix + COLON + msg);
        }
    }

    /**
     * Send a error log message.
     *
     * @param objectPrefix The object name you would like logged.
     * @param msg The message you would like logged.
     */
    public static void error(Object objectPrefix, String msg) {
        if (ERROR) {
            android.util.Log.e(TAG, getPrefixFromObject(objectPrefix) + COLON + msg);
        }
    }
}