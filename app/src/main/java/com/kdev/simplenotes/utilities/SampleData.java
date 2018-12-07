package com.kdev.simplenotes.utilities;

import com.kdev.simplenotes.database.NoteEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SampleData {

    private static final String SMALL_NOTE = "Cool Fancy Text Generator ";
    private static final String MIDL_NOTE = "Cool Fancy Text Generator is a copy and paste font generator and font " +
            "changer that creates Twitter, Facebook";
    private static final String LARGE_NOTE = "Cool Fancy Text Generator is a copy and paste font generator and font changer" +
            " that creates Twitter, Facebook, " + "Instagram fonts. It converts a normal text to different free" +
            " cool fonts styles, such as tattoo fonts, calligraphy fonts, web script fonts, cursive fonts, " +
            "handwriting fonts, old English fonts, word fonts, pretty fonts, font art...";


    private static Date getDate(int diff){
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.add(Calendar.MILLISECOND, diff);
        return calendar.getTime();
    }

    public static List<NoteEntity> getAllNotes(){
        List<NoteEntity> list = new ArrayList<>();
        list.add(new NoteEntity(getDate(0), SMALL_NOTE));
        list.add(new NoteEntity(getDate(-1), MIDL_NOTE));
        list.add(new NoteEntity(getDate(-2), LARGE_NOTE));
        return list;
    }

}
