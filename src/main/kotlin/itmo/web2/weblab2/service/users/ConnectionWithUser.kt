package itmo.web2.weblab2.service.users

import itmo.web2.weblab2.model.UsersInfo
import itmo.web2.weblab2.service.exception.NotSuchUserException
import java.security.MessageDigest
import java.util.*

class ConnectionWithUser {

    fun makeAnId():Long{
        var id: Long
        do {
            val md = Date()
            id = (md.time / 100000L)
        } while (UsersInfo.IdCollection.contains(id))
        return id
    }
    fun hashThePassword(pass:String):String{
        val md = MessageDigest.getInstance("MD5")
        md.update(pass.encodeToByteArray())
        val byteData = md.digest()
        val hexString = StringBuffer()
        for (aByteData in byteData) {
            val hex = Integer.toHexString(0xff and aByteData.toInt())
            if (hex.length == 1) hexString.append('0')
            hexString.append(hex)
        }
        return hexString.toString()
    }
    fun checkTheAuthorization(username:String, password:String):Boolean{
        for (User in UsersInfo.collectionWithUsers){
            if(User.takeUsername()==username){
                if(User.takePassword()==password){
                    return true
                }
                return false
            }
        }
        return false
    }
    fun checkUserExist(username:String):Boolean{
        for(User in UsersInfo.collectionWithUsers){
            if(User.takeUsername()==username){
                return true
            }
        }
        return false
    }
}