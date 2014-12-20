# Copyright (C) 2009 by Garmin Ltd. or its subsidiaries.

LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_PACKAGE_NAME := UiTestService

LOCAL_MODULE_TAGS := optional
LOCAL_CERTIFICATE := platform

LOCAL_SRC_FILES := $(call all-subdir-java-files)

include $(BUILD_PACKAGE)

