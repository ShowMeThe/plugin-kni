
fun rC4(keys: String, encrypt: String): String{
    val keyBytes = CharArray(256)
    val cypherBytes = CharArray(256)
    for (i in 0..255) {
        keyBytes[i] = keys[i % keys.length]
        cypherBytes[i] = i.toChar()
    }
    var jump = 0
    for (i in 0..255) {
        jump = (jump + cypherBytes[i].toInt() + keyBytes[i].toInt()) and 0xFF
        val tmp = cypherBytes[i] // Swap:
        cypherBytes[i] = cypherBytes[jump]
        cypherBytes[jump] = tmp
    }
    var i = 0
    jump = 0
    var s = ""
    for (element in encrypt) {
        i = i + 1 and 0xFF
        val tmp = cypherBytes[i]
        jump = jump + tmp.toInt() and 0xFF
        val t = (tmp.toInt() + cypherBytes[jump].toInt() and 0xFF).toChar()
        cypherBytes[i] = cypherBytes[jump] // Swap:
        cypherBytes[jump] = tmp
        s += String(charArrayOf((element.toInt() xor cypherBytes[t.toInt()].toInt()).toChar()))
    }
    return s
}


fun encode(offset:Long,text:String) : String{
    return with(StringBuilder()){
        //秘钥
        val key = offset
        //要加密的字符串
        val command= text
        //将字符串转换成为字符数组
        val charArray = command.toCharArray()
        charArray.forEach {
            //遍历每一个字符,获取ascii码
            var ascii = it.toInt()
            //对ascii进行偏移运算
            ascii += key.toInt()
            //ascii转成加密后的字符
            val result= ascii.toChar()
            //将加密后的字符拼接进stringbuild中
            append(result)
        }
        //返回stringbuild的tostring值
        toString()
    }
}
