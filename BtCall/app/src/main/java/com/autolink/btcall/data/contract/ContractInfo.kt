package com.autolink.btcall.data.contract

data class ContractInfo(val id: String, val name: String, val phone: String) : Comparable<ContractInfo> {
    private var pinYinForName = ""
    private var firstLetter = ""
    private var userAvatar = ""
    private var userName = ""
    private var userNick = ""
    private var isFriend = 0
    private var usrId = ""
    private var gradeLevel = 0
    private var userPosition = ""
    private var userCompany = ""
    private var userType = 0
    private var isUser = false

    override fun compareTo(other: ContractInfo): Int {
        return if (firstLetter == "#" && other.firstLetter != "#") {
            1
        } else if (firstLetter != "#" && other.firstLetter == "#"){
            -1
        } else {
            pinYinForName.compareTo(other.pinYinForName, true)
        }
    }

}
