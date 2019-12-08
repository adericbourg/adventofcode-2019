package net.dericbourg.adventofcode.y2019.d06

import java.util.*
import kotlin.Comparator

class OrbitGraph(
    val nodes: MutableMap<String, Node> = mutableMapOf(),
    val edges: MutableSet<Edge> = mutableSetOf()
) {
    companion object {
        fun buildGraph(orbits: List<Orbit>): OrbitGraph {
            val graph = OrbitGraph()
            graph.build(orbits)
            return graph
        }
    }

    fun build(orbits: List<Orbit>) {
        orbits.forEach { orbitRelation ->
            val centralMass = getNode(orbitRelation.centralMass)
            val orbitingObject = getNode(orbitRelation.orbitingObject)
            centralMass.children.add(orbitingObject)
            // Undirected
            edges.add(Edge(orbitingObject, centralMass))
            edges.add(Edge(centralMass, orbitingObject))
        }
    }

    private fun getNode(name: String): Node {
        nodes.putIfAbsent(name, Node(name))
        return nodes[name] as Node
    }

    fun countOrbits(): Int {
        return nodes.values
            .map { countNodeOrbits(it) }
            .sum()
    }

    private fun countNodeOrbits(node: Node): Int {
        return node.children.size + node.children.map { countNodeOrbits(it) }.sum()
    }

    fun shortestPath(from: String, to: String): Iterable<Node> {
        val dijkstra = Dijkstra(this, getNode(from))
        dijkstra.build()
        return dijkstra.getPathTo(getNode(to))
    }

    data class Node(val name: String, val children: MutableSet<Node> = mutableSetOf())
    data class Edge(val from: Node, val to: Node)
    
}

class Dijkstra(val graph: OrbitGraph, val source: OrbitGraph.Node) {

    private val distances: MutableMap<OrbitGraph.Node, Int> = mutableMapOf(Pair(source, 0))
    private val queue: Queue<OrbitGraph.Node> = PriorityQueue(
        graph.nodes.size,
        Comparator { n1: OrbitGraph.Node, n2: OrbitGraph.Node ->
            distances.getOrDefault(n1, infinity).compareTo(distances.getOrDefault(n2, infinity))
        }
    )
    private val prev: MutableMap<OrbitGraph.Node, Optional<OrbitGraph.Node>> = mutableMapOf()

    private val infinity = Int.MAX_VALUE / 2

    fun build() {
        graph.nodes.values
            .forEach { node ->
                queue.add(node)
                if (node != source) {
                    distances[node] = infinity
                    prev[node] = Optional.empty()
                }
            }
        while (queue.isNotEmpty()) {
            val min = queue.poll()
            neighbors(min).forEach { neighbor ->
                val alt = distances.getOrDefault(min, infinity) + 1
                if (alt < distances.getOrDefault(neighbor, infinity)) {
                    distances[neighbor] = alt
                    prev[neighbor] = Optional.of(min)
                }
            }
        }
    }

    fun getPathTo(node: OrbitGraph.Node): List<OrbitGraph.Node> {
        val buffer = mutableListOf<OrbitGraph.Node>()
        var current = node
        do {
            buffer.add(current)
            println(current.name)
            current = prev[current]!!.get()
        } while (current != source)
        return buffer.reversed()
    }

    private fun neighbors(node: OrbitGraph.Node): Iterable<OrbitGraph.Node> {
        return graph.edges.filter { it.from == node }.map { it.to }
    }
}
