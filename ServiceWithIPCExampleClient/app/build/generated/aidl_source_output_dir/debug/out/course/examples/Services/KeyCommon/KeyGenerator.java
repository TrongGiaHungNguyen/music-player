/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Using: /Users/tronggiahungnguyen/Library/Android/sdk/build-tools/35.0.0/aidl -p/Users/tronggiahungnguyen/Library/Android/sdk/platforms/android-34/framework.aidl -o/Users/tronggiahungnguyen/Documents/UIC/CS478/ServiceWithIPCExampleClient/app/build/generated/aidl_source_output_dir/debug/out -I/Users/tronggiahungnguyen/Documents/UIC/CS478/ServiceWithIPCExampleClient/app/src/main/aidl -I/Users/tronggiahungnguyen/Documents/UIC/CS478/ServiceWithIPCExampleClient/app/src/debug/aidl -I/Users/tronggiahungnguyen/.gradle/caches/8.10.2/transforms/7d44678d2b64a05099337cc0f45408c0/transformed/core-1.0.0/aidl -I/Users/tronggiahungnguyen/.gradle/caches/8.10.2/transforms/9dd2841aacf764b4d449e742e8a0721a/transformed/versionedparcelable-1.0.0/aidl -d/var/folders/t4/jwnrkty112ndjz1pmd6b5vd00000gn/T/aidl17982316036908922761.d /Users/tronggiahungnguyen/Documents/UIC/CS478/ServiceWithIPCExampleClient/app/src/main/aidl/course/examples/Services/KeyCommon/KeyGenerator.aidl
 */
package course.examples.Services.KeyCommon;
public interface KeyGenerator extends android.os.IInterface
{
  /** Default implementation for KeyGenerator. */
  public static class Default implements course.examples.Services.KeyCommon.KeyGenerator
  {
    @Override public java.lang.String[] listClips() throws android.os.RemoteException
    {
      return null;
    }
    @Override public void playClip(int index) throws android.os.RemoteException
    {
    }
    @Override public void stopClip() throws android.os.RemoteException
    {
    }
    @Override public void pauseClip() throws android.os.RemoteException
    {
    }
    @Override public void resumeClip() throws android.os.RemoteException
    {
    }
    @Override
    public android.os.IBinder asBinder() {
      return null;
    }
  }
  /** Local-side IPC implementation stub class. */
  public static abstract class Stub extends android.os.Binder implements course.examples.Services.KeyCommon.KeyGenerator
  {
    /** Construct the stub at attach it to the interface. */
    @SuppressWarnings("this-escape")
    public Stub()
    {
      this.attachInterface(this, DESCRIPTOR);
    }
    /**
     * Cast an IBinder object into an course.examples.Services.KeyCommon.KeyGenerator interface,
     * generating a proxy if needed.
     */
    public static course.examples.Services.KeyCommon.KeyGenerator asInterface(android.os.IBinder obj)
    {
      if ((obj==null)) {
        return null;
      }
      android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
      if (((iin!=null)&&(iin instanceof course.examples.Services.KeyCommon.KeyGenerator))) {
        return ((course.examples.Services.KeyCommon.KeyGenerator)iin);
      }
      return new course.examples.Services.KeyCommon.KeyGenerator.Stub.Proxy(obj);
    }
    @Override public android.os.IBinder asBinder()
    {
      return this;
    }
    @Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
    {
      java.lang.String descriptor = DESCRIPTOR;
      if (code >= android.os.IBinder.FIRST_CALL_TRANSACTION && code <= android.os.IBinder.LAST_CALL_TRANSACTION) {
        data.enforceInterface(descriptor);
      }
      if (code == INTERFACE_TRANSACTION) {
        reply.writeString(descriptor);
        return true;
      }
      switch (code)
      {
        case TRANSACTION_listClips:
        {
          java.lang.String[] _result = this.listClips();
          reply.writeNoException();
          reply.writeStringArray(_result);
          break;
        }
        case TRANSACTION_playClip:
        {
          int _arg0;
          _arg0 = data.readInt();
          this.playClip(_arg0);
          reply.writeNoException();
          break;
        }
        case TRANSACTION_stopClip:
        {
          this.stopClip();
          reply.writeNoException();
          break;
        }
        case TRANSACTION_pauseClip:
        {
          this.pauseClip();
          reply.writeNoException();
          break;
        }
        case TRANSACTION_resumeClip:
        {
          this.resumeClip();
          reply.writeNoException();
          break;
        }
        default:
        {
          return super.onTransact(code, data, reply, flags);
        }
      }
      return true;
    }
    private static class Proxy implements course.examples.Services.KeyCommon.KeyGenerator
    {
      private android.os.IBinder mRemote;
      Proxy(android.os.IBinder remote)
      {
        mRemote = remote;
      }
      @Override public android.os.IBinder asBinder()
      {
        return mRemote;
      }
      public java.lang.String getInterfaceDescriptor()
      {
        return DESCRIPTOR;
      }
      @Override public java.lang.String[] listClips() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.lang.String[] _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_listClips, _data, _reply, 0);
          _reply.readException();
          _result = _reply.createStringArray();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public void playClip(int index) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeInt(index);
          boolean _status = mRemote.transact(Stub.TRANSACTION_playClip, _data, _reply, 0);
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      @Override public void stopClip() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_stopClip, _data, _reply, 0);
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      @Override public void pauseClip() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_pauseClip, _data, _reply, 0);
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      @Override public void resumeClip() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_resumeClip, _data, _reply, 0);
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
    }
    static final int TRANSACTION_listClips = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    static final int TRANSACTION_playClip = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    static final int TRANSACTION_stopClip = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
    static final int TRANSACTION_pauseClip = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
    static final int TRANSACTION_resumeClip = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
  }
  /** @hide */
  public static final java.lang.String DESCRIPTOR = "course.examples.Services.KeyCommon.KeyGenerator";
  public java.lang.String[] listClips() throws android.os.RemoteException;
  public void playClip(int index) throws android.os.RemoteException;
  public void stopClip() throws android.os.RemoteException;
  public void pauseClip() throws android.os.RemoteException;
  public void resumeClip() throws android.os.RemoteException;
}
