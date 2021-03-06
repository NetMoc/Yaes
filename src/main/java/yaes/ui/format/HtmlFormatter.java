/*
   This file is part of the Xapagy project
   Created on: Apr 15, 2012
 
   com.xapagy.ui.prettyhtml.HtmlFormatter
 
   Copyright (c) 2008-2011 Ladislau Boloni
 */

package yaes.ui.format;

import java.util.Stack;


/**
 * @author Ladislau Boloni
 * 
 */
public class HtmlFormatter extends Formatter {
	   private enum OpenType {
	        A, BODY, DIV, H1, H2, H3, HTML, IMG, LI, P, PRE, SCRIPT, SPAN, TABLE,
	        TD, TR, UL
	    }

	    public static String getPreBlock(String text) {
	        HtmlFormatter fmt = new HtmlFormatter("");
	        fmt.addPre(text);
	        return fmt.toString();
	    }

	    /**
	     * Creates the opening bracket
	     * 
	     * @param name
	     * @param options
	     * @return
	     */
	    private static String makeOpening(String name, String... options) {
	        StringBuffer buf = new StringBuffer("<");
	        buf.append(name);
	        for (String option : options) {
	            buf.append(" " + option);
	        }
	        buf.delete(buf.length() - 1, buf.length() - 1);
	        buf.append(">");
	        return buf.toString();
	    };

	    private String cssFileName = "yaes.css";
	    private Stack<OpenType> stack;
	    private String title;

	    public HtmlFormatter() {
	        this.title = "";
	        stack = new Stack<OpenType>();
	    }

	    public HtmlFormatter(String title) {
	        this.title = title;
	        stack = new Stack<OpenType>();
	    }

	    public void addH1(String text, String... options) {
	        openH1(options);
	        add(text);
	        closeH1();
	    }

	    public void addH2(String text, String... options) {
	        openH2(options);
	        add(text);
	        closeH2();
	    }

	    public void addH3(String text, String... options) {
	        openH3(options);
	        add(text);
	        closeH3();
	    }

	    public void addImg(String... options) {
	        openImg(options);
	        closeImg();
	    }

	    public void addJavascript(String text) {
	        openScript("language=\"javascript\"", "type=\"text/javascript\"");
	        add(text);
	        closeScript();
	    }

	    public void addLI(String text, String... options) {
	        openLI(options);
	        add(text);
	        closeLI();
	    }

	    
	    public void addDiv(String text, String... options) {
	        openDiv(options);
	        add(text);
	        closeDiv();
	    }

	    
	    public void addP(String text, String... options) {
	        openP(options);
	        add(text);
	        closeP();
	    }

	    public void addPre(String text, String... options) {
	        openPre(options);
	        addNonIndented(text);
	        closePre();
	    }

	    public void closeA() {
	        closeGeneric(OpenType.A);
	    }

	    public void closeBody() {
	        closeGeneric(OpenType.BODY);
	    }

	    public void closeDiv() {
	        closeGeneric(OpenType.DIV);
	    }

	    public void closeGeneric(OpenType type) {
	        OpenType closed = stack.pop();
	        if (closed != type) {
	            throw new Error("Trying to close " + type
	                    + " but what I should close is " + closed);
	        }
	        deindent();
	        String label = closed.toString().toLowerCase();
	        add("</" + label + ">");
	    }

	    public void closeH1() {
	        closeGeneric(OpenType.H1);
	    }

	    public void closeH2() {
	        closeGeneric(OpenType.H2);
	    }

	    public void closeH3() {
	        closeGeneric(OpenType.H3);
	    }

	    public void closeHtml() {
	        closeGeneric(OpenType.HTML);
	    }

	    public void closeImg() {
	        closeGeneric(OpenType.IMG);
	    }

	    public void closeLI() {
	        closeGeneric(OpenType.LI);
	    }

	    public void closeP() {
	        closeGeneric(OpenType.P);
	    }

	    public void closePre() {
	        closeGeneric(OpenType.PRE);
	    }

	    public void closeScript() {
	        closeGeneric(OpenType.SCRIPT);
	    }

	    public void closeSpan() {
	        closeGeneric(OpenType.SPAN);
	    }

	    public void closeTable() {
	        closeGeneric(OpenType.TABLE);
	    }

	    public void closeTD() {
	        closeGeneric(OpenType.TD);
	    }

	    public void closeTR() {
	        closeGeneric(OpenType.TR);
	    }

	    public void closeUL() {
	        closeGeneric(OpenType.UL);
	    }

	    public void openA(String... options) {
	        openGeneric(OpenType.A, options);
	    }

	    public void openBody(String... options) {
	        openGeneric(OpenType.BODY, options);
	    }

	    public void openDiv(String... options) {
	        openGeneric(OpenType.DIV, options);
	    }

	    public void openGeneric(OpenType type, String... options) {
	        stack.push(type);
	        String label = type.toString().toLowerCase();
	        add(HtmlFormatter.makeOpening(label, options));
	        indent();
	    }

	    public void openH1(String... options) {
	        openGeneric(OpenType.H1, options);
	    }

	    public void openH2(String... options) {
	        openGeneric(OpenType.H2, options);
	    }

	    public void openH3(String... options) {
	        openGeneric(OpenType.H3, options);
	    }

	    public void openHtml(String... options) {
	        openGeneric(OpenType.HTML, options);
	        indent();
	        add("<head>");
	        add("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">");
	        add("<meta name=\"DESCRIPTION\" content=\"Xapagy\">");
	        add("<meta name=\"OWNER\" content=\"Lotzi Boloni\">");
	        add("<meta name=\"CREATED\" content=\"Lotzi Boloni 2012\">");
	        add("<title>" + title + "</title>");
	        add("<link HREF=\"" + cssFileName
	                + "\" REL=\"STYLESHEET\" LANG=\"en.us\">");
	        add("</head>");
	    }

	    public void openImg(String... options) {
	        openGeneric(OpenType.IMG, options);
	    }

	    public void openLI(String... options) {
	        openGeneric(OpenType.LI, options);
	    }

	    public void openP(String... options) {
	        openGeneric(OpenType.P, options);
	    }

	    public void openPre(String... options) {
	        openGeneric(OpenType.PRE, options);
	    }

	    public void openScript(String... options) {
	        openGeneric(OpenType.SCRIPT, options);
	    }

	    public void openSpan(String... options) {
	        openGeneric(OpenType.SPAN, options);
	    }

	    public void openTable(String... options) {
	        openGeneric(OpenType.TABLE, options);
	    }

	    public void openTD(String... options) {
	        openGeneric(OpenType.TD, options);
	    }

	    public void openTR(String... options) {
	        openGeneric(OpenType.TR, options);
	    }

	    public void openUL(String... options) {
	        openGeneric(OpenType.UL, options);
	    }
}
