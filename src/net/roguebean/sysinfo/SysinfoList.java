/*
 * SysinfoList.java, Aug 11, 2009
 *
 */
package net.roguebean.sysinfo;

import java.util.List;

/**
 * The <code>SysinfoList</code> class represents a single list data which has name and items.
 * 
 * @author Yonghwan Cho
 * @version 0.7
 */
class SysinfoList {
    
    final CharSequence name;
    
    final List<SysinfoListItem> items;
    
    SysinfoList(CharSequence name, List<SysinfoListItem> items) {
        this.name = name;
        this.items = items;
    }
    
}
