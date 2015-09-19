<%@ page import="org.voxmail.utils.AudioUtil" %><%
    System.out.println("Got to playMessage.jsp");
    System.out.println(request.getQueryString());
    AudioUtil au = new AudioUtil();
    au.streamAudio(request,response);
%>