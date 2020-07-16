import platform.android.ANDROID_LOG_INFO
import platform.android.__android_log_print

fun getTest(offset:Long,text:String) : String{
    return with(StringBuilder()){
        //秘钥
        val key = 2
        //将先前机密后的字符串转换成为字符数组
        val charArray= text.encodeToByteArray()
        charArray.forEach {
            //遍历每一个字符,获取ascii码
            var ascii = it.toInt()
            //对ascii进行反偏移运算
            ascii /= key.toInt()
            //ascii转成解密后的字符
            val result = ascii.toChar()
            //将加密后的字符拼接进stringbuild中
            append(result)
        }
        //返回stringbuild的tostring值
        toString()
    }
}

private fun log(any: Any){
    __android_log_print(ANDROID_LOG_INFO.toInt(), "222222222", "${any}")
}

fun createTest(offset:Long) : String{
    return with(StringBuilder()){
        //秘钥
        val key = 2
        //要加密的字符串
        val command="w55da4add13423asdas412dwwetgh"
        //将字符串转换成为字符数组
        val charArray = command.encodeToByteArray()
        charArray.forEach {
            //遍历每一个字符,获取ascii码
            var ascii = it.toInt()
            //对ascii进行偏移运算
            ascii *= key.toInt()
            //ascii转成加密后的字符
            val result= ascii.toChar()
            //将加密后的字符拼接进stringbuild中
            append(result)
        }
        //返回stringbuild的tostring值
        toString()
    }
}
