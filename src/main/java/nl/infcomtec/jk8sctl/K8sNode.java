/*
 *  Copyright (c) 2018 by Walter Stroebel and InfComTec.
 */
package nl.infcomtec.jk8sctl;

import io.kubernetes.client.models.V1ContainerImage;
import io.kubernetes.client.models.V1Node;
import java.util.TreeMap;

/**
 *
 * @author walter
 */
public class K8sNode extends AbstractMetadata {

    private V1Node k8s;

    public K8sNode(int mapId, V1Node k8s) {
        super(mapId, "node", k8s.getMetadata());
        this.k8s = k8s;
    }

    public long getTotalImageSize() {
        try {
            long iSize = 0;
            for (V1ContainerImage e : k8s.getStatus().getImages()) {
                if (null != e.getSizeBytes()) {
                    iSize += e.getSizeBytes();
                }
            }
            return iSize;
        } catch (Exception any) {
            return 0;
        }
    }

    @Override
    public StringBuilder getDotNode() {
        StringBuilder ret = pre();
        post(ret);
        return ret;
    }

    /**
     * @return the k8s
     */
    public V1Node getK8s() {
        return k8s;
    }

    @Override
    public TreeMap<Integer, String> getRelations() {
        TreeMap<Integer, String> ret = new TreeMap<>();
        ret.put(0, "node");
        return ret;
    }

    /**
     * @param k8s the k8s to set
     */
    public void setK8s(V1Node k8s) {
        this.k8s = k8s;
    }
}
