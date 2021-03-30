// ============================================================================
//
// Copyright (C) 2006-2021 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.utils.string;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/*
 * Created by bhe on Sep 22, 2020
 */
public class StudioContextUtilsTest {

    @Test
    public void testEncodeValue() throws Exception {
        String str = "\"\"";
        String actualStr = StudioContextUtils.encodeValue(str);
        assertEquals("Should equals", "", actualStr);

        str = "\"";
        actualStr = StudioContextUtils.encodeValue(str);
        assertEquals("Should equals", "\"", actualStr);

        str = "\"sample value\"";
        actualStr = StudioContextUtils.encodeValue(str);
        assertEquals("Should equals", "sample value", actualStr);

        str = "\"sample'\" value\"";
        actualStr = StudioContextUtils.encodeValue(str);
        assertEquals("Should equals", "sample'\" value", actualStr);

        str = "\"sample'\" \\u0041 \\\\t \\\\\" \\\\\\\\ \\\\r \\\\n \\\\b value\"";
        actualStr = StudioContextUtils.encodeValue(str);
        assertEquals("Should equals", "sample'\" A \\\t \\\" \\\\ \\\r \\\n \\\b value", actualStr);
    }

    @Test
    public void testRemoveQuotes() throws Exception {

        String quotedStr = "\"\"";
        String actualStr = StudioContextUtils.removeQuotes(quotedStr, StudioContextUtils.QUOTATION_MARK);
        assertEquals("Should equals", "", actualStr);

        quotedStr = "\"";
        actualStr = StudioContextUtils.removeQuotes(quotedStr, StudioContextUtils.QUOTATION_MARK);
        assertEquals("Should equals", "\"", actualStr);

        quotedStr = "\" ";
        actualStr = StudioContextUtils.removeQuotes(quotedStr, StudioContextUtils.QUOTATION_MARK);
        assertEquals("Should equals", " ", actualStr);

        quotedStr = "";
        actualStr = StudioContextUtils.removeQuotes(quotedStr, StudioContextUtils.QUOTATION_MARK);
        assertEquals("Should equals", "", actualStr);

        quotedStr = "\"sample value\"";
        actualStr = StudioContextUtils.removeQuotes(quotedStr, StudioContextUtils.QUOTATION_MARK);
        assertEquals("Should equals", "sample value", actualStr);

        quotedStr = "\"sample'\" value\"";
        actualStr = StudioContextUtils.removeQuotes(quotedStr, StudioContextUtils.QUOTATION_MARK);
        assertEquals("Should equals", "sample'\" value", actualStr);
    }

    @Test
    public void testRemoveQuotesIfExist() throws Exception {
        String quotedStr = "\"\"";
        String actualStr = StudioContextUtils.removeQuotesIfExist(quotedStr, StudioContextUtils.QUOTATION_MARK);
        assertEquals("Should equals", "", actualStr);

        quotedStr = "\"";
        actualStr = StudioContextUtils.removeQuotesIfExist(quotedStr, StudioContextUtils.QUOTATION_MARK);
        assertEquals("Should equals", "\"", actualStr);

        quotedStr = "\" ";
        actualStr = StudioContextUtils.removeQuotesIfExist(quotedStr, StudioContextUtils.QUOTATION_MARK);
        assertEquals("Should equals", " ", actualStr);

        quotedStr = "sample value\"";
        actualStr = StudioContextUtils.removeQuotesIfExist(quotedStr, StudioContextUtils.QUOTATION_MARK);
        assertEquals("Should equals", "sample value\"", actualStr);
    }

}
