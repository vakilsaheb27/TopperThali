package com.topperthali.mess.data;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.topperthali.mess.data.dao.MessDao;
import com.topperthali.mess.data.dao.MessDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class MessDatabase_Impl extends MessDatabase {
  private volatile MessDao _messDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `students_table` (`studentId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `phone` TEXT NOT NULL, `qrData` TEXT NOT NULL, `creditsRemaining` INTEGER NOT NULL, `isOnHoliday` INTEGER NOT NULL, `startDate` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `attendance_table` (`attendanceId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `studentId` INTEGER NOT NULL, `scanTimestamp` INTEGER NOT NULL, `mealType` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `payments_table` (`paymentId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `studentId` INTEGER NOT NULL, `amountPaid` REAL NOT NULL, `receiptNumber` TEXT NOT NULL, `paymentDate` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '537ef9287fa7fa577af910964b551b6c')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `students_table`");
        db.execSQL("DROP TABLE IF EXISTS `attendance_table`");
        db.execSQL("DROP TABLE IF EXISTS `payments_table`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsStudentsTable = new HashMap<String, TableInfo.Column>(7);
        _columnsStudentsTable.put("studentId", new TableInfo.Column("studentId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudentsTable.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudentsTable.put("phone", new TableInfo.Column("phone", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudentsTable.put("qrData", new TableInfo.Column("qrData", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudentsTable.put("creditsRemaining", new TableInfo.Column("creditsRemaining", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudentsTable.put("isOnHoliday", new TableInfo.Column("isOnHoliday", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudentsTable.put("startDate", new TableInfo.Column("startDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysStudentsTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesStudentsTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoStudentsTable = new TableInfo("students_table", _columnsStudentsTable, _foreignKeysStudentsTable, _indicesStudentsTable);
        final TableInfo _existingStudentsTable = TableInfo.read(db, "students_table");
        if (!_infoStudentsTable.equals(_existingStudentsTable)) {
          return new RoomOpenHelper.ValidationResult(false, "students_table(com.topperthali.mess.data.entities.StudentEntity).\n"
                  + " Expected:\n" + _infoStudentsTable + "\n"
                  + " Found:\n" + _existingStudentsTable);
        }
        final HashMap<String, TableInfo.Column> _columnsAttendanceTable = new HashMap<String, TableInfo.Column>(4);
        _columnsAttendanceTable.put("attendanceId", new TableInfo.Column("attendanceId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttendanceTable.put("studentId", new TableInfo.Column("studentId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttendanceTable.put("scanTimestamp", new TableInfo.Column("scanTimestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttendanceTable.put("mealType", new TableInfo.Column("mealType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAttendanceTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAttendanceTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAttendanceTable = new TableInfo("attendance_table", _columnsAttendanceTable, _foreignKeysAttendanceTable, _indicesAttendanceTable);
        final TableInfo _existingAttendanceTable = TableInfo.read(db, "attendance_table");
        if (!_infoAttendanceTable.equals(_existingAttendanceTable)) {
          return new RoomOpenHelper.ValidationResult(false, "attendance_table(com.topperthali.mess.data.entities.AttendanceEntity).\n"
                  + " Expected:\n" + _infoAttendanceTable + "\n"
                  + " Found:\n" + _existingAttendanceTable);
        }
        final HashMap<String, TableInfo.Column> _columnsPaymentsTable = new HashMap<String, TableInfo.Column>(5);
        _columnsPaymentsTable.put("paymentId", new TableInfo.Column("paymentId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPaymentsTable.put("studentId", new TableInfo.Column("studentId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPaymentsTable.put("amountPaid", new TableInfo.Column("amountPaid", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPaymentsTable.put("receiptNumber", new TableInfo.Column("receiptNumber", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPaymentsTable.put("paymentDate", new TableInfo.Column("paymentDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPaymentsTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPaymentsTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPaymentsTable = new TableInfo("payments_table", _columnsPaymentsTable, _foreignKeysPaymentsTable, _indicesPaymentsTable);
        final TableInfo _existingPaymentsTable = TableInfo.read(db, "payments_table");
        if (!_infoPaymentsTable.equals(_existingPaymentsTable)) {
          return new RoomOpenHelper.ValidationResult(false, "payments_table(com.topperthali.mess.data.entities.PaymentEntity).\n"
                  + " Expected:\n" + _infoPaymentsTable + "\n"
                  + " Found:\n" + _existingPaymentsTable);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "537ef9287fa7fa577af910964b551b6c", "1931155db48bdc85146adf7e6a4efb10");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "students_table","attendance_table","payments_table");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `students_table`");
      _db.execSQL("DELETE FROM `attendance_table`");
      _db.execSQL("DELETE FROM `payments_table`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(MessDao.class, MessDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public MessDao messDao() {
    if (_messDao != null) {
      return _messDao;
    } else {
      synchronized(this) {
        if(_messDao == null) {
          _messDao = new MessDao_Impl(this);
        }
        return _messDao;
      }
    }
  }
}
