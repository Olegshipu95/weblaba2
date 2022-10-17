package itmo.web2.weblab2.model

object UsersInfo {
    var collectionWithUsers = hashSetOf<User>()
    var IdCollection = hashSetOf<Long>()
    class User(private var id: Long, private var username: String, private var password: String){
        fun takeUsername():String{
            return username
        }
        fun takePassword():String{
            return password
        }
        override fun toString(): String {
            return "User(id=$id, username='$username', password='$password')"
        }
    }
}