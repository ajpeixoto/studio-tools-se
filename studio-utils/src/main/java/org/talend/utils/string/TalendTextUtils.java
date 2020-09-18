// ============================================================================
//
// Copyright (C) 2006-2020 Talend Inc. - www.talend.com
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

import org.apache.commons.lang3.StringEscapeUtils;

/*
 * Created by bhe on Sep 16, 2020
 */
public class TalendTextUtils {

    public static final String QUOTATION_MARK = "\"";

    private TalendTextUtils() {
    }

    public static String trimParameter(String value) {
        if (value == null) {
            return null;
        }
        int length = value.length();
        String result = removeQuotes(value, QUOTATION_MARK);
        if (length > 1
                && (((value.startsWith("\"") && value.endsWith("\""))) || (value.startsWith("\'") && value.endsWith("\'")))) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
            result = value.substring(1, length - 1);

            if (result.contains("\\")) { //$NON-NLS-1$
                result = result.replaceAll("\\\\n", "\n"); //$NON-NLS-1$ //$NON-NLS-2$
                result = result.replaceAll("\\\\b", "\b"); //$NON-NLS-1$ //$NON-NLS-2$
                result = result.replaceAll("\\\\f", "\f"); //$NON-NLS-1$ //$NON-NLS-2$
                result = result.replaceAll("\\\\r", "\r"); //$NON-NLS-1$ //$NON-NLS-2$
                result = result.replaceAll("\\\\t", "\t"); //$NON-NLS-1$ //$NON-NLS-2$
                result = result.replaceAll("\\\\\"", "\""); //$NON-NLS-1$ //$NON-NLS-2$
                result = result.replaceAll("\\\\\\\\", "\\\\"); //$NON-NLS-1$ //$NON-NLS-2$

                // handle unicode
                if (result.contains("\\u")) {
                    for (int indexStart = 0; result.indexOf("\\u", indexStart) >= 0; indexStart = result.indexOf("\\u",
                            indexStart)) {
                        if (result.indexOf("\\u", indexStart) + 5 <= result.length()) { //$NON-NLS-1$
                            int unicodeStart = result.indexOf("\\u"); //$NON-NLS-1$
                            int unicodeEnd = unicodeStart + 5;
                            result = result.substring(0, Math.max(0, unicodeStart))
                                    + StringEscapeUtils.unescapeJava(result.substring(unicodeStart, unicodeEnd + 1))
                                    + result.substring(Math.min(unicodeEnd + 1, result.length()), result.length());
                        }
                    }
                }
            }
        }

        return result;
    }

    public static String removeQuotes(String text, String quotation) {
        if (text == null) {
            return null;
        }

        if (text.length() > 1) {
            String substring = text.substring(0, 1);
            if (quotation.equals(substring)) {
                text = text.substring(1, text.length());
            }
            substring = text.substring(text.length() - 1, text.length());
            if (quotation.equals(substring)) {
                text = text.substring(0, text.length() - 1);
            }
        }
        return text;
    }

    public static String removeQuotesIfExist(String text, String quotation) {
        if (text == null) {
            return null;
        }
        if (!text.startsWith(quotation)) {
            return text;
        }

        return removeQuotes(text, quotation);
    }
}
