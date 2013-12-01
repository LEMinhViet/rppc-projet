/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rppc;

/**
 *
 * @author 3303340
 */
public class Node {
    Node gauche;
    Node droit;
    Sac sac;
    
    public Node() {
        sac = new Sac(200);
    }
    
    public Node(Sac last_sac) {
        sac = last_sac;
    }
}
