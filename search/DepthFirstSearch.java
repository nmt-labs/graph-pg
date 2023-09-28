package search;

public class DepthFirstSearch {
  // matriz [vertices v][4] -> guarda valor do vertice[v][0],  tt[v][1], td[v][2] e predecessor[v][3]
  // t = 0 -> inicia tempo globlal
  // iniciar tempos e predecessores
  // enquanto existir algum matriz[v][2] = 0 -> realizar busca em v

  // funcao de busca
  // t++
  // matriz[v][2] = t (td = t)
  // para todo vertice w vizinho de [v]
    // se matriz[w][2] = 0 (v nao descoberto)
      // aresta {v, w} -> arvore
      // matriz[w][3] = v
      // busca em w
    // senao se matriz[w][1] = 0 && w != matriz[v][3] (w nao acabou e nao e pai de v -> w e ancestral mas nao pai de v)
      // aresta {v, w} -> retorno
  // t++
  // matriz[v][1] = t (tt = t)
}
