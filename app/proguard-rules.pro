# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


 #腾讯定位SDK代码混淆
 -keep class c.t.**{*;}
 -keep class com.tencent.map.geolocation.**{*;}

 -keep public class com.tencent.location.**{
     public protected *;
 }
 -keepclasseswithmembernames class * {
     native <methods>;
 }

 -dontwarn  org.eclipse.jdt.annotation.**
 -dontwarn  c.t.**