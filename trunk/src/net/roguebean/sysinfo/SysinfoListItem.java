/*
 * SysinfoListItem.java, Aug 11, 2009
 *
 */
package net.roguebean.sysinfo;

/**
 * The <code>SysinfoListItem</code> class represents a single information item.
 * 
 * @author Yonghwan Cho
 * @version 0.7
 */
class SysinfoListItem {
    
    final CharSequence name;
    
    final CharSequence value;
    
    final CharSequence description;
    
    final CharSequence reference;
    
    SysinfoListItem(CharSequence name, CharSequence value, CharSequence description, CharSequence reference) {
        this.name = name;
        this.value = value;
        this.description = description;
        this.reference = reference;
    }
    
}
