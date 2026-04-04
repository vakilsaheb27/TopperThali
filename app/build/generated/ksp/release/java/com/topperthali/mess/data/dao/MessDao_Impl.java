package com.topperthali.mess.data.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.topperthali.mess.data.entities.AttendanceEntity;
import com.topperthali.mess.data.entities.PaymentEntity;
import com.topperthali.mess.data.entities.StudentEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class MessDao_Impl implements MessDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<StudentEntity> __insertionAdapterOfStudentEntity;

  private final EntityInsertionAdapter<AttendanceEntity> __insertionAdapterOfAttendanceEntity;

  private final EntityInsertionAdapter<PaymentEntity> __insertionAdapterOfPaymentEntity;

  private final EntityDeletionOrUpdateAdapter<StudentEntity> __deletionAdapterOfStudentEntity;

  private final EntityDeletionOrUpdateAdapter<StudentEntity> __updateAdapterOfStudentEntity;

  public MessDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfStudentEntity = new EntityInsertionAdapter<StudentEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `students_table` (`studentId`,`name`,`phone`,`qrData`,`creditsRemaining`,`isOnHoliday`,`startDate`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final StudentEntity entity) {
        statement.bindLong(1, entity.getStudentId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getPhone());
        statement.bindString(4, entity.getQrData());
        statement.bindLong(5, entity.getCreditsRemaining());
        final int _tmp = entity.isOnHoliday() ? 1 : 0;
        statement.bindLong(6, _tmp);
        statement.bindLong(7, entity.getStartDate());
      }
    };
    this.__insertionAdapterOfAttendanceEntity = new EntityInsertionAdapter<AttendanceEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `attendance_table` (`attendanceId`,`studentId`,`scanTimestamp`,`mealType`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AttendanceEntity entity) {
        statement.bindLong(1, entity.getAttendanceId());
        statement.bindLong(2, entity.getStudentId());
        statement.bindLong(3, entity.getScanTimestamp());
        statement.bindString(4, entity.getMealType());
      }
    };
    this.__insertionAdapterOfPaymentEntity = new EntityInsertionAdapter<PaymentEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `payments_table` (`paymentId`,`studentId`,`amountPaid`,`receiptNumber`,`paymentDate`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PaymentEntity entity) {
        statement.bindLong(1, entity.getPaymentId());
        statement.bindLong(2, entity.getStudentId());
        statement.bindDouble(3, entity.getAmountPaid());
        statement.bindString(4, entity.getReceiptNumber());
        statement.bindLong(5, entity.getPaymentDate());
      }
    };
    this.__deletionAdapterOfStudentEntity = new EntityDeletionOrUpdateAdapter<StudentEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `students_table` WHERE `studentId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final StudentEntity entity) {
        statement.bindLong(1, entity.getStudentId());
      }
    };
    this.__updateAdapterOfStudentEntity = new EntityDeletionOrUpdateAdapter<StudentEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `students_table` SET `studentId` = ?,`name` = ?,`phone` = ?,`qrData` = ?,`creditsRemaining` = ?,`isOnHoliday` = ?,`startDate` = ? WHERE `studentId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final StudentEntity entity) {
        statement.bindLong(1, entity.getStudentId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getPhone());
        statement.bindString(4, entity.getQrData());
        statement.bindLong(5, entity.getCreditsRemaining());
        final int _tmp = entity.isOnHoliday() ? 1 : 0;
        statement.bindLong(6, _tmp);
        statement.bindLong(7, entity.getStartDate());
        statement.bindLong(8, entity.getStudentId());
      }
    };
  }

  @Override
  public Object insertStudent(final StudentEntity student,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfStudentEntity.insert(student);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAttendance(final AttendanceEntity attendance,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAttendanceEntity.insert(attendance);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertPayment(final PaymentEntity payment,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPaymentEntity.insert(payment);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteStudent(final StudentEntity student,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfStudentEntity.handle(student);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateStudent(final StudentEntity student,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfStudentEntity.handle(student);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object getAllStudents(final Continuation<? super List<StudentEntity>> $completion) {
    final String _sql = "SELECT * FROM students_table ORDER BY name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<StudentEntity>>() {
      @Override
      @NonNull
      public List<StudentEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfStudentId = CursorUtil.getColumnIndexOrThrow(_cursor, "studentId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfQrData = CursorUtil.getColumnIndexOrThrow(_cursor, "qrData");
          final int _cursorIndexOfCreditsRemaining = CursorUtil.getColumnIndexOrThrow(_cursor, "creditsRemaining");
          final int _cursorIndexOfIsOnHoliday = CursorUtil.getColumnIndexOrThrow(_cursor, "isOnHoliday");
          final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "startDate");
          final List<StudentEntity> _result = new ArrayList<StudentEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final StudentEntity _item;
            final int _tmpStudentId;
            _tmpStudentId = _cursor.getInt(_cursorIndexOfStudentId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpPhone;
            _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            final String _tmpQrData;
            _tmpQrData = _cursor.getString(_cursorIndexOfQrData);
            final int _tmpCreditsRemaining;
            _tmpCreditsRemaining = _cursor.getInt(_cursorIndexOfCreditsRemaining);
            final boolean _tmpIsOnHoliday;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsOnHoliday);
            _tmpIsOnHoliday = _tmp != 0;
            final long _tmpStartDate;
            _tmpStartDate = _cursor.getLong(_cursorIndexOfStartDate);
            _item = new StudentEntity(_tmpStudentId,_tmpName,_tmpPhone,_tmpQrData,_tmpCreditsRemaining,_tmpIsOnHoliday,_tmpStartDate);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getStudentByQr(final String qrCode,
      final Continuation<? super StudentEntity> $completion) {
    final String _sql = "SELECT * FROM students_table WHERE qrData = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, qrCode);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<StudentEntity>() {
      @Override
      @Nullable
      public StudentEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfStudentId = CursorUtil.getColumnIndexOrThrow(_cursor, "studentId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfQrData = CursorUtil.getColumnIndexOrThrow(_cursor, "qrData");
          final int _cursorIndexOfCreditsRemaining = CursorUtil.getColumnIndexOrThrow(_cursor, "creditsRemaining");
          final int _cursorIndexOfIsOnHoliday = CursorUtil.getColumnIndexOrThrow(_cursor, "isOnHoliday");
          final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "startDate");
          final StudentEntity _result;
          if (_cursor.moveToFirst()) {
            final int _tmpStudentId;
            _tmpStudentId = _cursor.getInt(_cursorIndexOfStudentId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpPhone;
            _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            final String _tmpQrData;
            _tmpQrData = _cursor.getString(_cursorIndexOfQrData);
            final int _tmpCreditsRemaining;
            _tmpCreditsRemaining = _cursor.getInt(_cursorIndexOfCreditsRemaining);
            final boolean _tmpIsOnHoliday;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsOnHoliday);
            _tmpIsOnHoliday = _tmp != 0;
            final long _tmpStartDate;
            _tmpStartDate = _cursor.getLong(_cursorIndexOfStartDate);
            _result = new StudentEntity(_tmpStudentId,_tmpName,_tmpPhone,_tmpQrData,_tmpCreditsRemaining,_tmpIsOnHoliday,_tmpStartDate);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getAttendanceForStudent(final int studentId,
      final Continuation<? super List<AttendanceEntity>> $completion) {
    final String _sql = "SELECT * FROM attendance_table WHERE studentId = ? ORDER BY scanTimestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, studentId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AttendanceEntity>>() {
      @Override
      @NonNull
      public List<AttendanceEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfAttendanceId = CursorUtil.getColumnIndexOrThrow(_cursor, "attendanceId");
          final int _cursorIndexOfStudentId = CursorUtil.getColumnIndexOrThrow(_cursor, "studentId");
          final int _cursorIndexOfScanTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "scanTimestamp");
          final int _cursorIndexOfMealType = CursorUtil.getColumnIndexOrThrow(_cursor, "mealType");
          final List<AttendanceEntity> _result = new ArrayList<AttendanceEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AttendanceEntity _item;
            final int _tmpAttendanceId;
            _tmpAttendanceId = _cursor.getInt(_cursorIndexOfAttendanceId);
            final int _tmpStudentId;
            _tmpStudentId = _cursor.getInt(_cursorIndexOfStudentId);
            final long _tmpScanTimestamp;
            _tmpScanTimestamp = _cursor.getLong(_cursorIndexOfScanTimestamp);
            final String _tmpMealType;
            _tmpMealType = _cursor.getString(_cursorIndexOfMealType);
            _item = new AttendanceEntity(_tmpAttendanceId,_tmpStudentId,_tmpScanTimestamp,_tmpMealType);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
