package main;
import undirectedgraph.*;
import searchalgorithm.*;

import java.util.HashSet;
import java.util.List;

public class Main {

        public static void main(String[] args) {
            Graph graph = Romenia.defineGraph();
            //graph.showLinks();
            //graph.showSets();

           for (Algorithms dir: Algorithms.values()){
               //loop(graph,dir);
               pitStopSolution(graph,"Oradea","Moldova","Hirsova",dir);
           }
        }

        public static void loop(Graph graph,Algorithms dir){
            Node n;
            n = graph.searchSolution("Timisoara", "Neamt", dir);
            System.out.println(dir.name());
            graph.showSolution(n);
        }

        //a solução passa obricatoriamente por uma das cidades da provincia desponibilizada
        public static void pitStopSolution(Graph graph,String initLabel, String proviceLabel, String goalLabel,Algorithms dir){

            Graph newGraph = new Graph();
            //adicionando os vertices principal e final ao graph
            Vertex initVertex = new Vertex(initLabel);
            Vertex goalVertex = new Vertex(goalLabel);
            newGraph.addVertex(initLabel,initVertex.getLatitude() , initVertex.getLongitude());
            newGraph.addVertex(goalLabel,goalVertex.getLatitude() , goalVertex.getLongitude());

            //Procura dos vertices da provincias que será o pitStop
            HashSet<Vertex> provice = graph.getVertexOfSet(proviceLabel);
            //System.out.println(provice);//printe das cidades da provincia

            //Calculo e adição ao novo graph do caminho do vertice inical para cada um dos vertices encontrados
            // Calculo e adição ao novo graph do caminho de cada um dos vertices encontrados para o vertice final
                for (Vertex v: provice){
                    Node n,n2;
                    n = graph.searchSolution(initLabel, v.getLabel(), dir);
                    n2 = graph.searchSolution(goalLabel, v.getLabel(), dir);
                    double cost = n.getPathCost();
                    double cost2 = n2.getPathCost();
                    newGraph.addVertex(v.getLabel(), v.getLatitude(), v.getLongitude());
                    newGraph.addEdge(initLabel,v.getLabel(),cost);
                    newGraph.addEdge(goalLabel,v.getLabel(),cost2);
                }
                //System.out.println("********************Graph After First Funtion*********************");
                //newGraph.showLinks();
                //System.out.println("******************************************************************");

            //Calcilo da soluçao apartir do graph gerado
            Node n;
            n = newGraph.searchSolution(initLabel, goalLabel, dir);
            System.out.println(dir.name());
            graph.showSolution(n);
        }


}