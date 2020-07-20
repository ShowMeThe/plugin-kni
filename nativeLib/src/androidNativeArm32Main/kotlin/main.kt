import kotlinx.cinterop.*
import platform.android.*


var packageName :jstring? = null
var signNatureStr :jstring? = null
var context:jobject? = null
var key:String? = null

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
                context = pointer.NewWeakGlobalRef!!.invoke(env,pointer.CallStaticObjectMethodA!!.invoke(env,
                    applicationClz, currentApplicationMethodId,null))
                getPackage(env)
            }
        }
    }
}

private fun log(any: Any){
    __android_log_print(ANDROID_LOG_INFO.toInt(), "222222222", "${any}")
}

private fun getString(jstring: jstring,env: CPointer<JNIEnvVar>):String{
    return memScoped {
        val pointer = env.pointed.pointed!!
        pointer.GetStringUTFChars!!.invoke(env,jstring,null)!!.toKStringFromUtf8()
    }
}


fun getPackage(env: CPointer<JNIEnvVar>):jstring?{
    memScoped {
        val pointer = env.pointed.pointed!!
        val contextClz = pointer.GetObjectClass!!.invoke(env,context)
        val getPackageNameMethodId  = pointer.GetMethodID!!.invoke(
            env,contextClz,"getPackageName".cstr.ptr,"()Ljava/lang/String;".cstr.ptr)
        val name =  pointer.CallObjectMethodA!!.invoke(env,context,getPackageNameMethodId,null) as jstring
        packageName = pointer.NewGlobalRef!!.invoke(env,name)
        getInfo(env)
        return packageName
    }
}


fun getInfo(env: CPointer<JNIEnvVar>){
    memScoped {
        val pointer = env.pointed.pointed!!
        val context_clz = pointer.GetObjectClass!!.invoke(env,context)
        val get_package_manager_method_id = pointer.GetMethodID!!.invoke(env,context_clz,
            "getPackageManager".cstr.ptr,
            "()Landroid/content/pm/PackageManager;".cstr.ptr);
        val package_manager = pointer.CallObjectMethodA!!.invoke(env,context, get_package_manager_method_id,null)
        val package_manager_clz = pointer.GetObjectClass!!.invoke(env,package_manager)
        val get_package_info_method_id = pointer.GetMethodID!!.invoke(env,package_manager_clz,
            "getPackageInfo".cstr.ptr,
            "(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;".cstr.ptr)

        val array = allocArray<jvalue>(2)
        array[0].l = packageName
        array[1].i = 64
        val package_info  =
            pointer.CallObjectMethodA!!.invoke(env,package_manager, get_package_info_method_id,array)
        val  package_info_clz =
            pointer.GetObjectClass!!(env,package_info)
        val signatures_field_id =
            pointer.GetFieldID!!.invoke(env, package_info_clz, "signatures".cstr.ptr,
                "[Landroid/content/pm/Signature;".cstr.ptr)

        val signatures = pointer.GetObjectField!!.invoke(env,package_info,signatures_field_id) as jobjectArray
        val signature_clz = pointer.FindClass!!.invoke(env,"android/content/pm/Signature".cstr.ptr)

        val get_toCharsString_method_id = pointer.GetMethodID!!.invoke(env,
            signature_clz, "toCharsString".cstr.ptr, "()Ljava/lang/String;".cstr.ptr)

//        val size = pointer.GetArrayLength!!.invoke(env,signatures)
//        for(i in 0..size){
//        }
        val signature = pointer.GetObjectArrayElement!!.invoke(env,signatures, 0)
        val signature_char = pointer.CallObjectMethodA!!.
        invoke(env,signature, get_toCharsString_method_id,null)
        signNatureStr = pointer.NewWeakGlobalRef!!.invoke(env,signature_char)
        key = getString(signNatureStr!!,env)
    }
}


@CName("Java_com_show_plugin_EncryptCore_decode")
fun decode(env: CPointer<JNIEnvVar>,thiz:jobject ,text:jstring):jstring?{
    memScoped {
        val pointer = env.pointed.pointed!!
        val out = pointer.GetStringUTFChars!!.invoke(env,text,null)!!.toKStringFromUtf8()
        return pointer.NewStringUTF!!.invoke(env, rC4(getString(packageName!!,env),out).cstr.ptr)
    }
}


@CName("Java_com_show_plugin_EncryptCore_encode")
fun encode(env: CPointer<JNIEnvVar>,thiz:jobject ,text:jstring):jstring?{
    memScoped {
        val pointer = env.pointed.pointed!!
        val out = pointer.GetStringUTFChars!!.invoke(env,text,null)!!.toKStringFromUtf8()
        return pointer.NewStringUTF!!.invoke(env, rC4(getString(packageName!!,env),out).cstr.ptr)
    }
}



