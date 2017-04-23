1/ Un test unitaire doit �tre UNITAIRE
Cela para�t logique, mais bien souvent, cette r�gle n?est pas respect�e.
On teste une seule fonctionnalit�, un seul comportement.
Le test unitaire ne doit pas �tre d�pendant et ne doit pas faire appel aux webservices, aux bases de donn�es, aux syst�mes de fichiers

2/ Utiliser les mocks et/ou stubs (100% des appels externes)
L?utilisation des mocks ou des stubs permet justement de se concentrer sur le test d?une fonctionnalit�, d?un comportement de fa�on isol�e.
Ils permettent de simuler les objets, un retour base de donn�es, un webservice?


3/ Un test est avant tout du code
Il doit donc �tre simple, norm�, comment�, facile � lire et � comprendre puisqu?il doit �tre maintenable et permettre de comprendre la fonctionnalit� test�e : c?est la principale source de documentation.


4/ Le test doit �tre rapide � ex�cuter
En effet, une batterie de tests unitaires trop longue � ex�cuter vous ferait probablement h�siter � la lancer avant un commit


5/ Les tests doivent �tre �crits le plus t�t possible
Si vous pensez �crire les tests plus tard, la plupart du temps il n?y a jamais de plus tard !
Il est �galement important de comprendre que les tests vous aident � structurer vos d�veloppements (TDD).


6/ Un test = une assertion (ou presque)
Dans un test unitaire l?assertion sert � v�rifier que la sortie est bien celle attendue. Elle est cens�e �tre unique car on teste de fa�on unitaire.


7/ Tester en priorit� les parties critiques de l'application
Pragmatisme ! Tester de mani�re unitaire toute l?application n?a pas de sens. Les tests unitaires doivent se concentrer sur le coeur critique de l?application.


8/ Ecrire et refactoriser le code de l'application pour qu'il soit testable !
Il faut penser test m�me quand on �crit pas des tests. �crire un code testable du premier coup passe par la refactorisation qui permet d?�viter de dupliquer vos tests. En faisant cela, vous facilitez �galement la compr�hension de vos d�veloppements.


9/ Utiliser les design pattern
2 exemples :
? Utiliser les injections de d�pendances qui permettent de simuler ais�ment les objets externes au comportement.
? Utiliser le design pattern AAA qui permet d?organiser le test en 3 parties :
Arrange : Initialiser toutes les donn�es n�cessaires pour le test
Act : Tester le comportement
Assert : V�rifier que le r�sultat du test est conforme � la valeur attendue


10/ Le nom d'un test est capital ! Normalisez-le !
Pour une bonne compr�hension du test par vous et les autres d�veloppeurs, il est important que n?y ait aucun doute sur ce que fait le test. Pour cela, nous pr�conisons le format suivant :
test[NomMethode][EtatDeDepart][ComportementAttendu]
