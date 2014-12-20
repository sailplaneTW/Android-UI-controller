LOCAL_PATH:= $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional
LOCAL_MODULE_PATH := $(TARGET_OUT_DATA)/local/tmp

LOCAL_SRC_FILES := $(call all-java-files-under, src)

LOCAL_MODULE := UiAutomatorController
LOCAL_JAVA_LIBRARIES := uiautomator.core
LOCAL_PROGUARD_ENABLED := disabled

include $(BUILD_JAVA_LIBRARY)

