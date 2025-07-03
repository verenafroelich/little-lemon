package com.littlelemon.littlelemon;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import java.lang.Class;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class MenuItemDao_Impl implements MenuItemDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<MenuItem> __insertAdapterOfMenuItem;

  private final EntityDeleteOrUpdateAdapter<MenuItem> __deleteAdapterOfMenuItem;

  public MenuItemDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfMenuItem = new EntityInsertAdapter<MenuItem>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `MenuItem` (`id`,`title`,`price`,`description`,`image`,`category`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final MenuItem entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getTitle() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getTitle());
        }
        if (entity.getPrice() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getPrice());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getDescription());
        }
        if (entity.getImage() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getImage());
        }
        if (entity.getCategory() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getCategory());
        }
      }
    };
    this.__deleteAdapterOfMenuItem = new EntityDeleteOrUpdateAdapter<MenuItem>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `MenuItem` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final MenuItem entity) {
        statement.bindLong(1, entity.getId());
      }
    };
  }

  @Override
  public Object saveMenuItem(final MenuItem item, final Continuation<? super Unit> arg1) {
    if (item == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfMenuItem.insert(_connection, item);
      return Unit.INSTANCE;
    }, arg1);
  }

  @Override
  public Object deleteMenuItem(final MenuItem menuItem, final Continuation<? super Unit> arg1) {
    if (menuItem == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __deleteAdapterOfMenuItem.handle(_connection, menuItem);
      return Unit.INSTANCE;
    }, arg1);
  }

  @Override
  public LiveData<List<MenuItem>> getAllMenuItems() {
    final String _sql = "SELECT * FROM MenuItem";
    return __db.getInvalidationTracker().createLiveData(new String[] {"MenuItem"}, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "title");
        final int _columnIndexOfPrice = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "price");
        final int _columnIndexOfDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "description");
        final int _columnIndexOfImage = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "image");
        final int _columnIndexOfCategory = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "category");
        final List<MenuItem> _result = new ArrayList<MenuItem>();
        while (_stmt.step()) {
          final MenuItem _item;
          final int _tmpId;
          _tmpId = (int) (_stmt.getLong(_columnIndexOfId));
          final String _tmpTitle;
          if (_stmt.isNull(_columnIndexOfTitle)) {
            _tmpTitle = null;
          } else {
            _tmpTitle = _stmt.getText(_columnIndexOfTitle);
          }
          final String _tmpPrice;
          if (_stmt.isNull(_columnIndexOfPrice)) {
            _tmpPrice = null;
          } else {
            _tmpPrice = _stmt.getText(_columnIndexOfPrice);
          }
          final String _tmpDescription;
          if (_stmt.isNull(_columnIndexOfDescription)) {
            _tmpDescription = null;
          } else {
            _tmpDescription = _stmt.getText(_columnIndexOfDescription);
          }
          final String _tmpImage;
          if (_stmt.isNull(_columnIndexOfImage)) {
            _tmpImage = null;
          } else {
            _tmpImage = _stmt.getText(_columnIndexOfImage);
          }
          final String _tmpCategory;
          if (_stmt.isNull(_columnIndexOfCategory)) {
            _tmpCategory = null;
          } else {
            _tmpCategory = _stmt.getText(_columnIndexOfCategory);
          }
          _item = new MenuItem(_tmpId,_tmpTitle,_tmpPrice,_tmpDescription,_tmpImage,_tmpCategory);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public List<MenuItem> getAllMenuItemsNow() {
    final String _sql = "SELECT * FROM MenuItem";
    return DBUtil.performBlocking(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "title");
        final int _columnIndexOfPrice = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "price");
        final int _columnIndexOfDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "description");
        final int _columnIndexOfImage = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "image");
        final int _columnIndexOfCategory = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "category");
        final List<MenuItem> _result = new ArrayList<MenuItem>();
        while (_stmt.step()) {
          final MenuItem _item;
          final int _tmpId;
          _tmpId = (int) (_stmt.getLong(_columnIndexOfId));
          final String _tmpTitle;
          if (_stmt.isNull(_columnIndexOfTitle)) {
            _tmpTitle = null;
          } else {
            _tmpTitle = _stmt.getText(_columnIndexOfTitle);
          }
          final String _tmpPrice;
          if (_stmt.isNull(_columnIndexOfPrice)) {
            _tmpPrice = null;
          } else {
            _tmpPrice = _stmt.getText(_columnIndexOfPrice);
          }
          final String _tmpDescription;
          if (_stmt.isNull(_columnIndexOfDescription)) {
            _tmpDescription = null;
          } else {
            _tmpDescription = _stmt.getText(_columnIndexOfDescription);
          }
          final String _tmpImage;
          if (_stmt.isNull(_columnIndexOfImage)) {
            _tmpImage = null;
          } else {
            _tmpImage = _stmt.getText(_columnIndexOfImage);
          }
          final String _tmpCategory;
          if (_stmt.isNull(_columnIndexOfCategory)) {
            _tmpCategory = null;
          } else {
            _tmpCategory = _stmt.getText(_columnIndexOfCategory);
          }
          _item = new MenuItem(_tmpId,_tmpTitle,_tmpPrice,_tmpDescription,_tmpImage,_tmpCategory);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
