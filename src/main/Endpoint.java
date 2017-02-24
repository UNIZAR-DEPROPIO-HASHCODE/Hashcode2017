package main;
/**
 * Created by carlos, alberto, pablo y ruben on 23/02/17.
 */

public class Endpoint {
    private int id;
    private int lat;
    private CL [] caches;


    public Endpoint(int id, int lat, CL [] caches){
        this.id = id;
        this.lat = lat;
        quicksort(caches, 0, caches.length - 1);
        this.caches = caches;
    }

    //It returns the best cache for a video
    public Cache getCacheMin (Video video){
        for (int i = 0; i < caches.length; i++){
            Cache c = caches[i].getCache();
            boolean esta = c.isThere(video);
            if (c.getFreeSize() >= video.getSize() && !esta){
                return c;
            } else if (esta) return null;
        }
        return null;
    }

    public static void quicksort(CL A[], int izq, int der) {
        if (A.length > 1) {
            CL p = A[izq];
            int pivote = p.getLat(); // tomamos primer elemento como pivote
            int i = izq; // i realiza la búsqueda de izquierda a derecha
            int j = der; // j realiza la búsqueda de derecha a izquierda
            CL aux;

            while (i < j) {            // mientras no se crucen las búsquedas
                while (A[i].getLat() <= pivote && i < j) i++; // busca elemento mayor que pivote
                while (A[j].getLat() > pivote) j--;         // busca elemento menor que pivote
                if (i < j) {                      // si no se han cruzado
                    aux = A[i];                  // los intercambia
                    A[i] = A[j];
                    A[j] = aux;
                }
            }
            A[izq] = A[j]; // se coloca el pivote en su lugar de forma que tendremos
            A[j] = p; // los menores a su izquierda y los mayores a su derecha
            if (izq < j - 1)
                quicksort(A, izq, j - 1); // ordenamos subarray izquierdo
            if (j + 1 < der)
                quicksort(A, j + 1, der); // ordenamos subarray derecho
        }
    }
}