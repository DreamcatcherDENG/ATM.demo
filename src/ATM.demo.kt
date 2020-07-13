import java.util.*
import kotlin.collections.contains

val password = arrayOf(123,456,789)
val accounts = arrayOf(11111111,22222222,33333333)
val balance = arrayOf(120000,100000,200000)
val scanner = Scanner(System.`in`)


fun welcom(){
    println("******************欢迎来到建设银行************************")
}
fun login():Int {
    var flag = true
    print("请输入卡号：")
    var ac = scanner.nextInt()
    while(flag) {
        flag = if (!accounts.contains(ac)) {
            print("账号不存在!请重新输入:")
            ac = scanner.nextInt()
            true
        }else false
    }
    val index = accounts.indexOf(ac)
    for (i in 0..3) {
        if (i == 0) {
            print("请输入密码：")
        } else {
            print("密码错误,请重新输入:")
        }
        val pw = scanner.nextInt()
        if (pw == password[index]) {
            println("登录成功！")
            return ac
        }
    }
    println("登录失败！")
    return 0
}
fun operation() :Int {
    println("1.查看余额")
    println("2.取钱")
    println("3.存钱")
    println("4.转账")
    println("5.修改密码")
    println("6.退出")
    print("请选择操作:")
    val scanner = Scanner(System.`in`)
    return scanner.nextInt()
}
fun checkoutbalance(account :Int){
    println("你的余额为：${balance[accounts.indexOf(account)]}")
}
fun withdraw(account :Int){
    var flag = true
    while(flag) {
        print("请输入取款金额:")
        val money = scanner.nextInt()
        if (money <= balance[accounts.indexOf(account)]) {
            balance[accounts.indexOf(account)] = balance[accounts.indexOf(account)] - money
            print("是否继续提款Y/N:")
            flag = scanner.next() == "Y"
        } else {
            checkoutbalance(account)
            println("你的余额不足！请重新输入:")
        }
    }
}
fun savaMoney(account: Int){
    var flag = true
    print("请输入存款金额:")
    while(flag) {
        val money = scanner.nextInt()
        balance[accounts.indexOf(account)] = balance[accounts.indexOf(account)] + money
        print("是否继续存款Y/N:")
        flag = if (scanner.next() == "Y") {
            print("请输入存款金额:")
            true
        } else false
    }
}

fun transferacount(account: Int){
    var flag = true
    while(flag) {
        print("请输入转账账户:")
        var otherAccount = scanner.nextInt()
        if(accounts.contains(otherAccount)) {
            print("请输入转账金额:")
            var money = scanner.nextInt()
            if(money <= balance[accounts.indexOf(account)]) {
                balance[accounts.indexOf(account)] = balance[accounts.indexOf(account)] - money
                balance[accounts.indexOf(otherAccount)] = balance[accounts.indexOf(otherAccount)] + money
                print("转账成功,是否继续转账Y/N:")
                scanner.nextLine()
                flag = scanner.nextLine() == "Y"
            }else{
                println("你的余额不足!")
                checkoutbalance(account)
            }
        }else{
            println("账户不存在！请重新输入账户!")
        }
    }
}

fun changepassword(account: Int) {
    while (true) {
        print("请输入新密码:")
        val pwnew1 = scanner.nextInt()
        print("请再次确实新密码:")
        val pwnew2 = scanner.nextInt()
        if (pwnew1 == pwnew2) {
            password[accounts.indexOf(account)] = pwnew1
            println("密码更改成功！")
            return
        } else {
            println("两次输入的密码不相同！请重新输入！")
        }
    }
}

fun main(){
    welcom()
    val account = login()
    if(account == 0){
        return
    }
    while(true) {
        when (operation()) {
            1 -> checkoutbalance(account)
            2 -> withdraw(account)
            3-> savaMoney(account)
            4-> transferacount(account)
            5-> changepassword(account)
            else -> {
                println("退出登录!")
                return
            }
        }
    }

}

