package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by carlos, alberto, pablo y ruben on 23/02/17.
 */
public class Main {

    public static void main (String [] args) {
        try {
            //Initialization
            Scanner sc = new Scanner(new File("videos_worth_spreading.in") );
            int nVideos, nEndpoints, nRequests;
            String linea;
            String [] info = new String [5];
            //Read first Line and save info
            linea = sc.nextLine();
            info = linea.split(" ");
            nVideos = Integer.parseInt(info[0]);
            nEndpoints = Integer.parseInt(info[1]);
            nRequests = Integer.parseInt(info[2]);
            Cache[] caches = getCaches(info);
            //Read Line with videos
            Video [] aVideo = readVideos(sc, nVideos);
            //Read Endpoints
            Endpoint [] aEndpoints = readEndpoints(sc, nEndpoints, caches);
            //Read Requests info
            Requests [] aRequest = readRequestsInfo(sc, nRequests, aVideo, aEndpoints);

            quicksort(aRequest, 0, aRequest.length - 1);

            distribuirCache(aRequest);
            generarFicheroSalida(caches);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Requests[] readRequestsInfo(Scanner sc, int nRequests, Video[] aVideo, Endpoint[] aEndpoints) {
        String [] requestInfo;
        Requests [] aRequest = new Requests [nRequests];
        for (int i = 0; i < nRequests; i++) {
            requestInfo = sc.nextLine().split(" ");
            int idVideo = Integer.parseInt(requestInfo[0]);
            int idEnd = Integer.parseInt(requestInfo[1]);
            int number = Integer.parseInt(requestInfo[2]);
            aRequest[i] = (new Requests(aVideo[idVideo], aEndpoints[idEnd], number));
        }
        return aRequest;

//
    }

    private static void distribuirCache (Requests [] aRequestOrd) {
        for (int i = aRequestOrd.length - 1; i >= 0; i--) {
            Cache cacheMin = aRequestOrd[i].getEndpoint().getCacheMin(aRequestOrd[i].getVideo());
            if (cacheMin != null) 	{
                cacheMin.addVideo(aRequestOrd[i].getVideo());
            }
        }
    }

    private static Endpoint[] readEndpoints(Scanner sc, int nEndpoints, Cache[] caches) {
        Endpoint [] aEndPoints = new Endpoint[nEndpoints];
        String linea;
        String [] endpoints, infoCaches;
        for (int i = 0; i < nEndpoints; i++) {
            //Read Lat + nCaches per Endpoint.
            linea = sc.nextLine();
            endpoints = linea.split(" ");
            //Create Cache-Lat object for each Endpoint.
            CL [] cl = new CL [Integer.parseInt(endpoints[1])];
            //Read info of Caches for each EndPoint.
            for (int j = 0; j < Integer.parseInt(endpoints[1]); j++) {
                linea = sc.nextLine();
                infoCaches = linea.split(" ");
                int idCache = Integer.parseInt(infoCaches[0]);
                int latEnd = Integer.parseInt(infoCaches[1]);
                cl[j] = new CL(caches[idCache], latEnd);
            }
            aEndPoints[i] = new Endpoint(i, Integer.parseInt(endpoints[0]), cl);

        }
        return aEndPoints;
    }

    private static Video[] readVideos(Scanner sc, int nVideos) {
        Video [] aVideo = new Video [nVideos];
        String linea;
        linea = sc.nextLine();
        String [] videoInfo = linea.split(" ");
        for (int i = 0; i < videoInfo.length; i++) {
            aVideo[i] = new Video(i, Integer.parseInt(videoInfo[i]));
        }
        return aVideo;
    }

    private static Cache[] getCaches(String[] info) {
        Cache [] caches = new Cache [Integer.parseInt(info[3])];

        for (int i = 0; i < caches.length; i++) {
            caches[i] = new Cache(i, Integer.parseInt(info[4]));
        }
        return caches;
    }

    private static void outInfo(int nVideos, int nEndpoints, int nRequests, Cache[] caches) {
        System.out.println(nVideos + "-" + nEndpoints + "-" + nRequests);
        for (int i = 0; i < caches.length; i++) {
            System.out.println(caches[i].getId() + "-" + caches[i].getSize());
        }
    }


    private static void quicksort(Requests A[], int izq, int der) {
        if (A.length > 1) {
            Requests p = A[izq];
            int pivote = p.getNumberR(); // tomamos primer elemento como pivote
            int i = izq; // i realiza la búsqueda de izquierda a derecha
            int j = der; // j realiza la búsqueda de derecha a izquierda
            Requests aux;

            while (i < j) {            // mientras no se crucen las búsquedas
                while (A[i].getNumberR() <= pivote && i < j) i++; // busca elemento mayor que pivote
                while (A[j].getNumberR() > pivote) j--;         // busca elemento menor que pivote
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

    private static void generarFicheroSalida (Cache[] caches){
        try{
            int usedCaches = 0;
            String auxLine = "";

            for(int i= 0; i< caches.length; i++) {
                if (!caches[i].getVideos().isEmpty()) {
                    usedCaches++;
                    auxLine += i;
                    for (Video v: caches[i].getVideos()){
                        auxLine += " " + v.getId();
                    }
                    auxLine += "\n";
                }
            }

            auxLine = usedCaches + "\n" + auxLine;

            PrintWriter writer = new PrintWriter("salidatxt", "UTF-8");
            writer.print(auxLine);
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
