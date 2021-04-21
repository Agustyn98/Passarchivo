package com.example.passarchivo.password

import android.util.Base64
import java.security.MessageDigest
import java.util.*


class PasswordManager() {

    //find good hashing algo
    fun hashPassword(password: String): String? {
        var sha1: String? = ""
        val password = "$password¥"
        try {
            val crypt = MessageDigest.getInstance("SHA-1")
            crypt.reset()
            crypt.update(password.toByteArray(charset("UTF-8")))
            sha1 = byteToHex(crypt.digest())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return sha1
    }

    private fun byteToHex(hash: ByteArray): String? {
        val formatter = Formatter()
        for (b in hash) {
            formatter.format("%02x", b)
        }
        val result: String = formatter.toString()
        formatter.close()
        return result
    }

    //random password generator
    companion object {
        fun getRandomString(length: Int = 12): String {
            val allowedChars =
                ('A'..'Z') + ('a'..'z') + ('0'..'9') + '+' + '-' + '/' + '*' + '!' + '@' + '?'
            return (1..length)
                .map { allowedChars.random() }
                .joinToString("")
        }

        fun encrypt(pass: String) : String {

            val encodedBytes: ByteArray = Base64.encode(pass.toByteArray(), 1)
            val encodedPass = String(encodedBytes)
            val passLength = encodedPass.length
            var encryptedPass = ""

            for (i in 0 until passLength) {
                if (encodedPass[i] in 'A'..'Y') {
                    encryptedPass += (encodedPass[i] + 1)
                } else if (encodedPass[i] == 'Z') {
                    encryptedPass += 'A'
                } else {
                    encryptedPass += encodedPass[i]
                }
            }
            return encryptedPass
        }

        fun decrypt(pass: String) : String{

            val passLength = pass.length
            var decryptedPass = ""

            for (i in 0 until passLength) {
                if (pass[i] in 'B'..'Z') {
                    decryptedPass += (pass[i] - 1)
                } else if (pass[i] == 'A') {
                    decryptedPass += 'Z'
                } else {
                    decryptedPass += pass[i]
                }
            }
            return try{
                val decryptedPassDecoded = Base64.decode(decryptedPass,1) // returns ByteArray
                String(decryptedPassDecoded)
            }catch (e : Exception){
                println("error: " + e.message)
                "Error parsing Base64"
            }
        }
    }

}