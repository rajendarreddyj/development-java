/*
 * The contents of this file are subject to the Mozilla Public
 * License Version 1.1 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of
 * the License at http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS
 * IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * rights and limitations under the License.
 *
 * The Original Code is vox-mail.
 *
 * The Initial Developer of the Original Code is Voxeo Corporation.
 * Portions created by Voxeo are Copyright (C) 2000-2007.
 * All rights reserved.
 * 
 * Contributor(s):
 * ICOA Inc. <info@icoa.com> (http://icoa.com)
 */

/*
 * MessageWrapper.java
 *
 * Created on January 16, 2007, 3:09 PM
 */

package org.voxmail.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.mail.Flags;
import javax.mail.Message;

/**
 * @author James
 */
public class MessageWrapper implements Comparable {
    
    /** Creates a new instance of MessageWrapper */
    public MessageWrapper(Message message) {
        
        this.message = message;
        this.init();
    }
    
    public static final int MESSAGE_STATUS_NEW = 0;
    public static final int MESSAGE_STATUS_SKIPPED = 1;
    public static final int MESSAGE_STATUS_SAVED = 2;
    public static final int MESSAGE_STATUS_DELETED = 3;
    
    public static final int PLAY_FIRST_MESSAGE = 0;
    public static final int PLAY_FIRST_NEW_MESSAGE = 1;
    public static final int PLAY_NEXT_MESSAGE = 2;
    public static final int PLAY_FIRST_SKIPPED = 3;
    
    private Message message = null;
    private int status = 0;
    
    public int getMessageNumber()
    {
        return message.getMessageNumber();
    }

    public int getStatus()
    {
        return status;
    }
    
    public String getAudioFileName(String messagePath)
    {
        String messageFilename = "";
        String fileLocation="";
        try {
            messageFilename = message.getHeader("X-Message-Path")[0];
            if (messageFilename == null || messageFilename.equals(""))
            {
                return "";
            }
                
            //change \ to /
            messageFilename = messageFilename.replace('\\', '/');
            messageFilename = messageFilename.substring(messageFilename.lastIndexOf("/") + 1);
            fileLocation = messagePath + "/" + messageFilename;

        } catch (Exception e) {
            //e.printStackTrace();
        }
        System.out.println("getAudioFileName: " + fileLocation);
        return fileLocation;
    }
    
    public String getCallerIdText()
    {
        String callerID = "Unkown";
        try
        {
             callerID = message.getHeader("X-Caller-Id")[0];
        }
        catch (Exception e)
        {
            //e.printStackTrace();
        }
        return callerID;
    }
    
    public Date getReceivedDate()
    {
        Date received = new Date();
        try
        {
            received = message.getReceivedDate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return received;
    }
    
    public String getDatetimeText()
    {
        String datetime = "";
        try
        {
            Date date = message.getReceivedDate();
            datetime = MessageWrapper.getMonthDayText(date) + " at " + 
                    MessageWrapper.getTimeText(date);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return datetime;
    }
    
    public String getAtTimeText()
    {
        Date date = this.getReceivedDate();
        String text = "at" + MessageWrapper.getTimeText(date);
        return text;
    }
    
    public static String getTimeText(Date date)
    {
        String strDate = null;
        try {
            strDate = new SimpleDateFormat("h m a").format(date);
            if (strDate.endsWith("PM"))
            {
                strDate = strDate.substring(0,strDate.length()-2) + "P M.";
            }
            else
            {
                strDate = strDate.substring(0,strDate.length()-2) + "A M.";
            }
        } catch (Exception e) { }
        
        return strDate;
    }
    public static String getMonthDayText(Date date)
    {
        String strDate = null;
        try {
            strDate = new SimpleDateFormat("MMMMMMMMMM d ").format(date);
            
        } catch (Exception e) { }
        
        return strDate;
    }
    
    public String getMonthDayText()
    {
        Date date = this.getReceivedDate();
        return MessageWrapper.getMonthDayText(date);
    }
    
    public String getMonth()
    {
        Date date = this.getReceivedDate();
        String strDate = "";
        try {
            strDate = new SimpleDateFormat("MMMMMMMMMM").format(date);
            
        } catch (Exception e) { }
        
        return strDate;
    }
    
    public String getOrdinalDay()
    {
        Date date = this.getReceivedDate();
        String strDate = "";
        int day = 0;
        try {
            strDate = new SimpleDateFormat("d").format(date);
            day = Integer.parseInt(strDate);
            
        } catch (Exception e) { }
        
        if (day == 1)
        {
            return "First";
        }
        else if (day ==2)
        {
            return "Second";
        }
        else if (day==3)
        {
            return "Third";
        }
        else if (day==4)
        {
            return "Fourth";
        }
        else if (day==5)
        {
            return "Fifth";
        }
        else if (day==6)
        {
            return "Sixth";
        }
        else if (day==7)
        {
            return "Seventh";
        }
        else if (day==8)
        {
            return "Eighth";
        }
        else if (day==9)
        {
            return "Ninth";
        }
        else if (day==10)
        {
            return "Tenth";
        }
        else if (day==11)
        {
            return "Eleventh";
        }
        else if (day==12)
        {
            return "Twelfth";
        }
        else if (day==13)
        {
            return "Thirteenth";
        }
        else if (day==14)
        {
            return "Fourteenth";
        }
        else if (day==15)
        {
            return "Fifteenth";
        }
        else if (day==16)
        {
            return "Sixteenth";
        }
        else if (day==17)
        {
            return "Seventeenth";
        }
        else if (day==18)
        {
            return "Eighteenth";
        }
        else if (day==19)
        {
            return "Nineteenth";
        }
        else if (day==20)
        {
            return "Twentieth";
        }
        else if (day==21)
        {
            return "TwentyFirst";
        }
        else if (day==22)
        {
            return "TwentySecond";
        }
        else if (day==23)
        {
            return "TwentyThird";
        }
        else if (day==24)
        {
            return "TwentyFourth";
        }
        else if (day==25)
        {
            return "TwentyFifth";
        }
        else if (day==26)
        {
            return "TwentySixth";
        }
        else if (day==27)
        {
            return "TwentySeventh";
        }
        else if (day==28)
        {
            return "TwentyEighth";
        }
        else if (day==29)
        {
            return "TwentyNinth";
        }
        else if (day==30)
        {
            return "Thirtieth";
        }
        else if (day==31)
        {
            return "ThirtyFirst";
        }
            
        
        return strDate;
    }
    
    
    public String getMessageDateText()
    {
        String dateText = "";
        Date date = new Date();
        try
        {
            date = message.getReceivedDate();
            dateText = MessageWrapper.getTimeText(date);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
//        if (MessageWrapper.isToday(date))
//        {
//            dateText = " today at ";
//            dateText = dateText + MessageWrapper.getTimeText(date);
//        }
//        else if (MessageWrapper.isYesterday(date))
//        {
//            dateText= " yesterday at ";
//            dateText = dateText + MessageWrapper.getTimeText(date);
//        }
//        else
//        {
//            dateText = " on ";
//            dateText = dateText + MessageWrapper.getMonthDayText(date);
//        }
        return dateText;
    }
    
    public static boolean isToday(Date date)
    {
        Calendar today = Calendar.getInstance();
        Calendar msgDate = Calendar.getInstance();
        msgDate.setTime(date);
        
        if (today.get(Calendar.DAY_OF_YEAR) == msgDate.get(Calendar.DAY_OF_YEAR) &&
                today.get(Calendar.YEAR) == msgDate.get(Calendar.YEAR))
        {
            return true;
        }
        
        return false;
    }
    
    public boolean isToday()
    {
        Date date = this.getReceivedDate();
        return MessageWrapper.isToday(date);
    }
    
    public boolean isYesterday()
    {
        Date date = this.getReceivedDate();
        return MessageWrapper.isYesterday(date);
    }
    
    public static boolean isYesterday(Date date)
    {
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DAY_OF_YEAR,-1);
        
        Calendar msgDate = Calendar.getInstance();
        msgDate.setTime(date);
        
        if (yesterday.get(Calendar.DAY_OF_YEAR) == msgDate.get(Calendar.DAY_OF_YEAR) &&
                yesterday.get(Calendar.YEAR) == msgDate.get(Calendar.YEAR))
        {
            return true;
        }
        
        return false;
    }
    
   
    
    public static int getStartMessageType(boolean playedNew, boolean playedSkipped, MessageWrapper mw, boolean hasSkippedMessages)
    {
        if (mw.getStatus() == MessageWrapper.MESSAGE_STATUS_NEW)
        {
            if (playedNew == false)
            {
                if (hasSkippedMessages)
                {
                    return PLAY_FIRST_NEW_MESSAGE;
                }
                else
                {
                    return PLAY_FIRST_MESSAGE;
                }
            }
            else 
            {
                return PLAY_NEXT_MESSAGE;
            }
        }
        else if (mw.getStatus() == MessageWrapper.MESSAGE_STATUS_SKIPPED)
        {
            if (playedSkipped == false)
            {
                return PLAY_FIRST_SKIPPED;
            }
            else
            {
                return PLAY_NEXT_MESSAGE;
            }
        }
        else if (mw.getStatus() == MessageWrapper.MESSAGE_STATUS_SAVED)
        {
            if (playedNew == false)
            {
                return PLAY_FIRST_MESSAGE;
            }
            else
            {
                return PLAY_NEXT_MESSAGE;
            }
        }
        else
        {
            return 1;
        }
    }
    
    private void init()
    {
        try
        {
            Flags flags = message.getFlags();
            String[] userFlags = flags.getUserFlags();
            
            if (message.isSet(Flags.Flag.FLAGGED))
            {
                System.out.println("Msg: " + message.getMessageNumber() + ", FLAGGED flag, setting to skipped");
                status = MessageWrapper.MESSAGE_STATUS_SKIPPED;
            }
            else if (message.isSet(Flags.Flag.SEEN))
            {
               System.out.println("Msg: " + message.getMessageNumber() + ", SEEN flag, setting to saved");
               status = MessageWrapper.MESSAGE_STATUS_SAVED;
            }
            else if (message.isSet(Flags.Flag.DELETED))
            {
                System.out.println("Msg: " + message.getMessageNumber() + ", DELETED flag, setting to deleted");
                status = MessageWrapper.MESSAGE_STATUS_DELETED;
            }
            
            else
            {
                System.out.println("Msg: " + message.getMessageNumber() + ", No flags, setting to new");
                status = MessageWrapper.MESSAGE_STATUS_NEW;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public int compareTo(Object ob) throws ClassCastException // Order by Date
    {
        MessageWrapper temp = (MessageWrapper)ob; 
        Date date1 = null, date2 = null;

        try 
        {
            date1 = this.getReceivedDate();
            date2 = temp.getReceivedDate();
        }
        catch(Exception e) 
        { 
            e.printStackTrace(); 
        }

        if ((date1!=null)&&(date2!=null) )
            return date2.compareTo(date1);
        else
            return 0;
    }

    
    
}
