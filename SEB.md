1/ Un test unitaire doit être UNITAIRE
Cela paraît logique, mais bien souvent, cette règle n?est pas respectée.
On teste une seule fonctionnalité, un seul comportement.
Le test unitaire ne doit pas être dépendant et ne doit pas faire appel aux webservices, aux bases de données, aux systèmes de fichiers

2/ Utiliser les mocks et/ou stubs (100% des appels externes)
L?utilisation des mocks ou des stubs permet justement de se concentrer sur le test d?une fonctionnalité, d?un comportement de façon isolée.
Ils permettent de simuler les objets, un retour base de données, un webservice?


3/ Un test est avant tout du code
Il doit donc être simple, normé, commenté, facile à lire et à comprendre puisqu?il doit être maintenable et permettre de comprendre la fonctionnalité testée : c?est la principale source de documentation.


4/ Le test doit être rapide à exécuter
En effet, une batterie de tests unitaires trop longue à exécuter vous ferait probablement hésiter à la lancer avant un commit


5/ Les tests doivent être écrits le plus tôt possible
Si vous pensez écrire les tests plus tard, la plupart du temps il n?y a jamais de plus tard !
Il est également important de comprendre que les tests vous aident à structurer vos développements (TDD).


6/ Un test = une assertion (ou presque)
Dans un test unitaire l?assertion sert à vérifier que la sortie est bien celle attendue. Elle est censée être unique car on teste de façon unitaire.


7/ Tester en priorité les parties critiques de l'application
Pragmatisme ! Tester de manière unitaire toute l?application n?a pas de sens. Les tests unitaires doivent se concentrer sur le coeur critique de l?application.


8/ Ecrire et refactoriser le code de l'application pour qu'il soit testable !
Il faut penser test même quand on écrit pas des tests. Écrire un code testable du premier coup passe par la refactorisation qui permet d?éviter de dupliquer vos tests. En faisant cela, vous facilitez également la compréhension de vos développements.


9/ Utiliser les design pattern
2 exemples :
? Utiliser les injections de dépendances qui permettent de simuler aisément les objets externes au comportement.
? Utiliser le design pattern AAA qui permet d?organiser le test en 3 parties :
Arrange : Initialiser toutes les données nécessaires pour le test
Act : Tester le comportement
Assert : Vérifier que le résultat du test est conforme à la valeur attendue


10/ Le nom d'un test est capital ! Normalisez-le !
Pour une bonne compréhension du test par vous et les autres développeurs, il est important que n?y ait aucun doute sur ce que fait le test. Pour cela, nous préconisons le format suivant :
test[NomMethode][EtatDeDepart][ComportementAttendu]
