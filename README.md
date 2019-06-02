# MDI : Design Pattern

## Partie 1 : le Pattern Observer

Dans la première partie de ce TP, nous avons mis en place le pattern observer. Ce pattern est utile quand on dois mettre à jour l'état d'un objet(Une interface graphique par exemple) en fonction de l'état d'un autre objet(ici une boite mail). L'avantage qu'offre ce pattern est de déléguer la tâche de mise à jour à l'objet **observé**. En effet, plutôt que de verifier continuellement l'état de l'objet **observé** pour en modifier la vue, nous créons des classes **observateurs** qui vont s'inscrire à l'objet **observé** comme à une newsletter. Une fois cela fait, l'objet **observé** pourra **notifier** les **observateurs** inscrit à chaque fois que son état sera modifié, ce qui évite donc les vérification constante et permet aussi d'avoir plusieurs types d'observateurs différents sans avoir à re-coder toute la logique de récupération des données qui est déjà dans la classe abstraite **Observateur**.

## Partie 2 : le Pattern Visiteur

Malheureusement, nous avons perdu le code fait en TP, c'est pourquoi nous essayerons de rentrer plus dans les détails pour ce pattern

Le pattern visiteur est un pattern très utilisé pour faire du parcours de données. En effet, sa structure permet d'effectuer des traitement sur des données dont on ne connais pas a priori le type d'instance mais juste de son interface. Par exemple, un arbre de la syntaxe abstraite(AST) à un ensemble de noeud de type connu, mais sa structure globale est très libre et ne répond pas à une forme stricte. C'est dans ce genre de ca ou le pattern visiteur est très fort, car il permet de sortir les opérations de chaque classe de noeud pour les réunir dans une seule; le **visiteur**. Il suffit de reunir les traitement de chacun des types de noeud dans le visiteur et de faire en sorte que tous les noeuds de la structure est une fonction prenant un visiteur en paramètre, ainsi quand on voudras traiter l'arbre il suffira d'appeller cette dernière avec un visiteur concret le noeud se chargera d'appeller la fonction du visiteur correspondant a son type.

Petit apparté, nous avons tous plus ou moins déjà implémenté ce pattern au cours de notre cours de compilation que cela soit dans le cadre de notre pretty-printer, qui était la première étape de notre projet, ou dans le compilateur/transpileur réalisé dans la seconde moitié du cours. L'équipe GAPY à aussi utilisé ce design pattern pour la génération de topologie. La classe abstraite `Topology` correspond à l'interface commune des visiteurs. Chaque topologie est donc en soi un visiteur concret. Pour le coté structure, la seule structure étant le `ProjectManager` les visiteurs n'implémentent qu'une fonction correspondant à celui-ci. Les fonctions `addTopology` du manager et `create` des topologies sont donc équivalent à `accept(v:Visitor)` et `visitProjectManager(p:ProjectManager)`

L'avantage conséquent de ce pattern est la facilité à mettre en place de nouveaux traitement sur un même type de structure. En effet, si vous avez déjà créé un pretty-printer en utilisant ce pattern, implémenter un évaluateur, un transpileur ou un compilateur reviendra à créer de nouveaux visiteur concret et de **correctement** implémenter chacune des fonctions nécéssaire. 

Toutefois, ce design pattern à un inconvénient non négligeable. En effet, une modification de la structure(ajout d'un type de noeud par exemple) obligera à modifier l'ensemble des visiteurs concret pour s'adapter à ce changement.

## Partie 3 : Pattern Decorator et State

Dans cette partie nous faisons une étude de cas sur le projet de réalisation de la plateforme de commande de pizza de GothamPizza

![](https://github.com/Gwandalff/MDI-TP-DesignPattern/blob/master/images/question1.png)

### Question 1 : Expliquer les erreurs de l'exemple

Pour commencer, c'est incompréhensible. Il n'y à pas de structure à proprement parler et tous les objets sont plus ou moins interconnectés. 
Cette structure sera sans le moindre doute dur à maintenir et il sera très difficile d'ajouter de nouvelles fonctionalitée.

### Question 2 : Pattern Decorator

![](https://github.com/Gwandalff/MDI-TP-DesignPattern/blob/master/images/question2.png)

L'avantage du pattern decorator est sa capacité à pouvoir ajouter dynamiquement des fonctionnalité à un objet sans perdre l'instance originale. De plus, les classe décoratrices hérite de la classe principale ce qui permet de les cumuler. Par exemple sur le diagramme, on peux voir qu'une commande peut devenir une commande en retard ou une commande en happy hour et c'est précisément sur ce genre de cas ou le pattern decorator est meilleur que l'héritage. En effet, si une commande en happy hour est en retard, avec un simple héritage on dois créé une classe `happyHourRetard` qui herite de happy hour et qui implémente la fonctionnalité de retard(il serais malin de créer une interface retard a implementer dans ce cas précis) ce qui n'est pas pratique si on ajoute plein de types de commande il faudras à chaque fois créer son équivalent en retard. Pour le pattern decorator le problème est simple à résoudre, une `commandeHappyHour` étant une `Commande` elle convient totalement pour instancier une `commandeRetardee`, dans le cas ou nous avons plusieurs type de commandes héritant toutes de `Commande`, il suffira de coder **une seule fois** `CommandeRetardee` et elle pourra s'instancier avec n'importe quelle commande.

### Question 3 : Pattern State

![](https://github.com/Gwandalff/MDI-TP-DesignPattern/blob/master/images/question3.png)

Pour cette question, nous utilisons le pattern state qui est parfaitement adapté car il permet d'encapsuler dans des objet correspondant à un état des processus métier. Par exemple, `startWork` assignera un pointPizza si l'état est `Commandee` mais appelera le livreur si on est dans l'état `EnCoursDePreparation` et les `nextState` passerons à l'étape suivante du processus métier sans ce soucier de l'état dans le quel on est c'est l'état qui gerera la transition.
