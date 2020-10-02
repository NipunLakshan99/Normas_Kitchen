package com.example.madcustomer.Database;


import android.provider.BaseColumns;

public final class UserProfile {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private UserProfile() {}

    /* Inner class that defines the table contents */
    public static class Users implements BaseColumns {
        public static final String TABLE_NAME = "UserInfo";
        public static final String COLUMN_1 = "UserName";
        public static final String COLUMN_2 = "Phone";
        public static final String COLUMN_3 = "Address";
        public static final String COLUMN_4 = "ContactNo";
        public static final String COLUMN_5 = "Email";
        public static final String COLUMN_6 = "Gender";
        public static final String COLUMN_7 = "Password";
        public static final String COLUMN_8 = "Description";
        //public static final String COLUMN_9 = "dob";
        //public static final String COLUMN_10 = "amount";
        //public static final String COLUMN_11 = "ptype";
    }
}
