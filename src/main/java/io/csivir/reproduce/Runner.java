package io.csivir.reproduce;

import io.fabric8.kubernetes.api.model.GenericKubernetesResource;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;
import io.fabric8.kubernetes.client.informers.ResourceEventHandler;

public class Runner {


    public static void main(String[] args) throws InterruptedException {
        var client = new KubernetesClientBuilder().build();

        var informer = client.genericKubernetesResources("v1","ConfigMap").inAnyNamespace().runnableInformer(0);

        informer.addEventHandler(new ResourceEventHandler<>() {
            @Override
            public void onAdd(GenericKubernetesResource genericKubernetesResource) {
                System.out.println("Kind: "+genericKubernetesResource.getKind());
            }

            @Override
            public void onUpdate(GenericKubernetesResource genericKubernetesResource, GenericKubernetesResource t1) {
                System.out.println("Kind: "+genericKubernetesResource.getKind());
            }

            @Override
            public void onDelete(GenericKubernetesResource genericKubernetesResource, boolean b) {
                System.out.println("Kind: "+genericKubernetesResource.getKind());
            }
        });
        informer.run();
    }

}
