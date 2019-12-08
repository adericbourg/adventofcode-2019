package net.dericbourg.adventofcode.y2019.d06

class OrbitGraph(private val nodes: MutableMap<String, Node> = mutableMapOf()) {
    companion object {
        fun countOrbits(orbits: List<Orbit>): Int {
            val graph = OrbitGraph()
            graph.build(orbits)

            return graph.nodes.values
                .map { countNodeOrbits(it) }
                .sum()
        }

        private fun countNodeOrbits(node: Node): Int {
            return node.children.size + node.children.map { countNodeOrbits(it) }.sum()
        }
    }

    fun build(orbits: List<Orbit>) {
        orbits.forEach { orbitRelation ->
            val centralMass = getNode(orbitRelation.centralMass)
            val orbitingObject = getNode(orbitRelation.orbitingObject)
            centralMass.children.add(orbitingObject)
        }
    }

    private fun getNode(name: String): Node {
        nodes.putIfAbsent(name, Node(name))
        return nodes[name] as Node
    }

    data class Node(val name: String, val children: MutableSet<Node> = mutableSetOf())
}