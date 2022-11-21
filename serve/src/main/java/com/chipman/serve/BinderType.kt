package com.chipman.serve

enum class BinderType(private val type: Int) {
    MESSAGE(0),
    MUSIC(1),
    ;

    companion object {
        fun getValue(type: Int): BinderType? {
            for (binderType in values()) {
                if (binderType.type == type) {
                    return binderType
                }
            }
            return null
        }
    }
}

//public enum BinderType {
//
//    MESSAGE(0),
//    MUSIC(1),
//    ;
//
//    private int type;
//
//    BinderType(int type) {
//        this.type = type;
//    }
//
//    public int getType() {
//        return type;
//    }
//
//    public static BinderType getValue(int type) {
//        for (BinderType binderType : BinderType.values()) {
//            if (binderType.type == type) {
//                return binderType;
//            }
//        }
//        return null;
//    }
//}