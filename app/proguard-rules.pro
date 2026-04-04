# ProGuard Rules for Room Database and Debug Logging

# Keep Room database classes
-keep class * extends androidx.room.RoomDatabase {
    <init>(...);
}

# Keep Room entities and data classes
-keep class * {
    @androidx.room.PrimaryKey <fields>;
    @androidx.room.Entity <methods>;
}

# Keep model classes with annotations
-keep class com.example.app.models.** { *; }

# Keep data classes
-keep class com.example.app.data.** { *; }

# Remove debug logging in release builds
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** w(...);
    public static *** i(...);
    public static *** e(...);
}

# Add any other ProGuard rules necessary for your app's release build.