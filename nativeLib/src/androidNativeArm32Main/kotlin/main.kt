import kotlinx.cinterop.*
import platform.android.*


var packageName :jstring? = null

@CName("Java_com_show_plugin_EncryptCore_initCore")
fun initCore(env: CPointer<JNIEnvVar>){
    memScoped {
        val pointer = env.pointed.pointed!!
        val applicationClz = pointer.FindClass!!.invoke(env,"android/app/ActivityThread".cstr.ptr)
        if(applicationClz !=null){
            val currentApplicationMethodId = pointer.GetStaticMethodID!!.invoke(env,
                applicationClz,
                "currentApplication".cstr.ptr,
                "()Landroid/app/Application;".cstr.ptr
            )
            if (currentApplicationMethodId !== null) {
               val context = pointer.CallStaticObjectMethodA!!.invoke(env,
                    applicationClz, currentApplicationMethodId,null)
                getPackage(env, context)
            }
        }
    }
}

private fun log(any: Any){
    __android_log_print(ANDROID_LOG_INFO.toInt(), "222222222", "${any}")
}

private fun getKey(env: CPointer<JNIEnvVar>):String{
   return memScoped {
       val pointer = env.pointed.pointed!!
       pointer.GetStringUTFChars!!.invoke(env,packageName,null)!!.toKStringFromUtf8()
   }
}


fun getPackage(env: CPointer<JNIEnvVar>,context:jobject?):jstring?{
   memScoped {
       val pointer = env.pointed.pointed!!
       val contextClz = pointer.GetObjectClass!!.invoke(env,context)
       val getPackageNameMethodId  = pointer.GetMethodID!!.invoke(
           env,contextClz,"getPackageName".cstr.ptr,"()Ljava/lang/String;".cstr.ptr)
       val name =  pointer.CallObjectMethodA!!.invoke(env,context,getPackageNameMethodId,null) as jstring
       packageName = pointer.NewGlobalRef!!.invoke(env,name)
       return packageName
   }
}



@ExperimentalStdlibApi
@CName("Java_com_show_plugin_EncryptCore_get")
fun get(env: CPointer<JNIEnvVar>,thiz: jobject,text:jstring):jstring?{
    memScoped {
        val pointer = env.pointed.pointed!!
        val out = pointer.GetStringUTFChars!!.invoke(env,text,null)!!.toKStringFromUtf8()
        val key = getKey(env)
        log(key)
        return pointer.NewStringUTF!!.invoke(env, RC4(key,out).cstr.ptr)
    }
}


@ExperimentalStdlibApi
@CName("Java_com_show_plugin_EncryptCore_getTest")
fun getTest(env: CPointer<JNIEnvVar>):jstring?{
    memScoped {
        val pointer = env.pointed.pointed!!
        val key = getKey(env)
        log(key)
        return pointer.NewStringUTF!!.invoke(env, RC4(key,"ILoo").cstr.ptr)
    }
}



