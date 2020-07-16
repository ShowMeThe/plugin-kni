
fun RC4(keys: String, encrypt: String): String{
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
    var Result = ""
    for (x in 0 until encrypt.length) {
        i = i + 1 and 0xFF
        val tmp = cypherBytes[i]
        jump = jump + tmp.toInt() and 0xFF
        val t = (tmp.toInt() + cypherBytes[jump].toInt() and 0xFF).toChar()
        cypherBytes[i] = cypherBytes[jump] // Swap:
        cypherBytes[jump] = tmp
        Result += String(charArrayOf((encrypt[x].toInt() xor cypherBytes[t.toInt()].toInt()).toChar()))
    }
    return Result
}