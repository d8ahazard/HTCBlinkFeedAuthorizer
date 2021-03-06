package com.woalk.apps.xposed.htcblinkfeedauthorizer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * The class to be loaded by Xposed.
 */
public class X_Mod implements IXposedHookLoadPackage, IXposedHookZygoteInit {
    public static final String PKG_HTC_LAUNCHER = "com.htc.launcher";
	public static final String CLASS_BF_HELPER = "com.htc.launcher.util.HspUpdateHelper";
	public static final String CLASS_BF_SETTINGUTIL = "com.htc.launcher.util.SettingUtil";
	public static final String CLASS_BF_LIB2 = "com.htc.lib2.Hms";
	public static final String CLASS_BF_LIB0 = "com.htc.lib0.HDKLib0Util";
	public static final String CLASS_BF_UDACT = "com.htc.socialnetwork.common.utils.ui.HMSUpdateActivity";
	public static final String CLASS_BF_LOCK = "com.htc.blinklock.BlinkLockProvider";
    public static final String CLASS_BF_PROFILEBRIEF = "com.htc.themepicker.model.ProfileBrief";
	
	public static final String PKG_HTC_GALLERY = "com.htc.album";
	public static final String CLASS_3DSCENE = "com.htc.sunny2.frameworks.base.widgets.SunnyScene";
	
	public static final String PKG_HTC_CAMERA = "com.htc.camera";
	
	public static final String PKG_HTC_FB = "com.htc.sense.socialnetwork.facebook";
	public static final String CLASS_METHOD_E = "com.htc.socialnetwork.facebook.FacebookBaseActivity";
	
	public static final String PKG_HTC_GPLUS = "com.htc.socialnetwork.googleplus";
	public static final String CLASS_GPLUS_MAINACTIVITY = "com.htc.socialnetwork.googleplus.GooglePlusActivity";
	public static final String CLASS_GPLUS_HMSUPDATE = "com.htc.socialnetwork.googleplus.HMSUpdateActivity";
	
	public static final String PKG_HTC_INSTAGRAM = "com.htc.sense.socialnetwork.instagram";
	
	public static final String PKG_HTC_LIB0 = "com.htc.lib0";
    public static final String PKG_HTC_SOCIALNETWORK_UI = "com.htc.socialnetwork.common.utils.ui";

    public static final String CLASS_HDK0UTIL = PKG_HTC_LIB0 + ".HDKLib0Util";
    public static final String CLASS_BASE_ACTIVITY = PKG_HTC_SOCIALNETWORK_UI + ".BaseActivity";
    public static final String CLASS_COMMON_MF_MAIN_ACTIVITY = PKG_HTC_SOCIALNETWORK_UI +
            ".CommonMfMainActivity";
    
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
		// First section contains common checks found in all HTC Apps
		// Need to see if OR statements are best, or if we can just check for com.htc.* apps
        if (lpparam.packageName.equals(PKG_HTC_LAUNCHER)) {
            XposedHelpers.findAndHookMethod(CLASS_HDK0UTIL, lpparam.classLoader, "getSenseVersion",
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            param.setResult(7.0f);
                        }
                    });
					
			XposedHelpers.findAndHookMethod(CLASS_HDK0UTIL, lpparam.classLoader, "getHDKBaseVersion",
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            param.setResult(19.2f); //Guessing at this value, need to investigate
                        }
                    });

            XposedHelpers.findAndHookMethod(CLASS_HDK0UTIL, lpparam.classLoader, "isHTCDevice",
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            param.setResult(true);
                        }
                    });
			
			XposedHelpers.findAndHookMethod(CLASS_BF_UDACT, lpparam.classLoader, "onCreate", Bundle.class,
					new XC_MethodHook() {
						@Override
						protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
							((Activity) param.thisObject).getIntent().setAction("ANY_ACTION");
						}
					});
			
							
			XposedHelpers.findAndHookMethod(CLASS_HDK0UTIL, lpparam.classLoader, "isStockUIDevice",
                    Context.class, new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            param.setResult(Boolean.FALSE);
                        }
                    });
		
		    XposedHelpers.findAndHookMethod(CLASS_BF_HELPER, lpparam.classLoader, "isHSPCompatible",
                    Context.class, new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            param.setResult(Boolean.TRUE);
                        }
                    });
					
			XposedHelpers.findAndHookMethod(CLASS_BF_SETTINGUTIL, lpparam.classLoader, "isHtcDevice",
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            param.setResult(true);
                        }
                    });
			
			XposedHelpers.findAndHookMethod(CLASS_HDK0UTIL, lpparam.classLoader, "isHEPDevice",
                    Context.class, new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            param.setResult(Boolean.TRUE);
                        }
                    });
					
			//I suspect this is critical
			

			XposedHelpers.findAndHookMethod(CLASS_BF_LIB2, lpparam.classLoader, "isHtcDevice",
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            param.setResult(true);
                        }
                    });
					
            XposedHelpers.findAndHookMethod(CLASS_HDK0UTIL, lpparam.classLoader, "isODMDevice",
                    Context.class, new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            param.setResult(Boolean.FALSE);
                        }
                    });
            
            XposedHelpers.findAndHookMethod(CLASS_BASE_ACTIVITY, lpparam.classLoader,
                    "checkCompatibility", new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            param.setResult(true);
                        }
                    });
					
			XposedHelpers.findAndHookMethod(CLASS_COMMON_MF_MAIN_ACTIVITY, lpparam.classLoader,
                    "checkCompatibility", new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            param.setResult(true);
                        }
                    });
					
			XposedHelpers.findAndHookMethod(CLASS_BF_LOCK, lpparam.classLoader,
                    "checkPermission", new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            param.setResult(true);
                        }
                    });

            XposedHelpers.findAndHookMethod(CLASS_BF_PROFILEBRIEF, lpparam.classLoader,
                    "isHtc", new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            param.setResult(true);
                        }
                    });

            XposedHelpers.findAndHookMethod(CLASS_BF_PROFILEBRIEF, lpparam.classLoader, "isHtc",
                    CLASS_BF_PROFILEBRIEF, new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            param.setResult(true);
                        }
                    });
					
        } else if (lpparam.packageName.equals(PKG_HTC_GALLERY)) {
			//This check enables duo fx - tie to a toggle in UI
			XposedHelpers.findAndHookMethod(CLASS_3DSCENE, lpparam.classLoader,
                "enable3dScene", new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        param.setResult(Boolean.TRUE);
                        }
                    });
			 XposedHelpers.findAndHookMethod(CLASS_HDK0UTIL, lpparam.classLoader, "getSenseVersion",
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            param.setResult(7.0f);
                        }
                    });
					
			XposedHelpers.findAndHookMethod(CLASS_HDK0UTIL, lpparam.classLoader, "getHDKBaseVersion",
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            param.setResult(6.0F); //Guessing at this value, need to investigate
                        }
                    });
            try
            {
                XposedHelpers.findAndHookMethod(CLASS_HDK0UTIL, lpparam.classLoader, "isHTCDevice",
                        new XC_MethodHook() {
                            @Override
                            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                param.setResult(true);
                            }
                        });

                XposedHelpers.findAndHookMethod(CLASS_HDK0UTIL, lpparam.classLoader, "isStockUIDevice",
                        Context.class, new XC_MethodHook() {
                            @Override
                            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                param.setResult(Boolean.FALSE);
                            }
                        });
            }
            catch (Throwable e)
            {
                e.printStackTrace();
            }

		} else if (lpparam.packageName.equals(PKG_HTC_FB)) {
			XposedHelpers.findAndHookMethod(CLASS_METHOD_E, lpparam.classLoader,
                "e", new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        param.setResult(true);
                        }
                    });

		} else if (lpparam.packageName.equals(PKG_HTC_GPLUS)) {
			XposedHelpers.findAndHookMethod(CLASS_GPLUS_MAINACTIVITY, lpparam.classLoader,
                "f", new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        param.setResult(true);
                        }
                    });
			
			XposedHelpers.findAndHookMethod(CLASS_GPLUS_HMSUPDATE, lpparam.classLoader,
                "g", new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        param.setResult(null);
                        }
                    });

        }
    }

    public static final String CLASS_ANDROID_CONTEXT = "android.content.ContextWrapper";

    @Override
    public void initZygote(StartupParam startupParam) throws Throwable {
        XposedHelpers.findAndHookMethod(CLASS_ANDROID_CONTEXT, null, // = use system ClassLoader
                "sendBroadcast", Intent.class, String.class, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        if (((String) param.args[1]).startsWith("com.htc.sense.permission.")) {
                            param.args[1] = null;
                        }
                    }
                });
    }
}
