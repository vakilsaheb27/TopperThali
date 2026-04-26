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

# Keep all models, entities, and DAOs in your specific package
-keep class com.topperthali.mess.data.** { *; }

# Remove debug logging in release builds
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** w(...);
    public static *** i(...);
    public static *** e(...);
    public static *** v(...);
}

# Keep Kotlin Coroutines correctly
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
