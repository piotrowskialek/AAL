# AAL
Analiza Algorytmów projekt

Aleksander Piotrowski 5AR

Problem: 
	Ułożenie pojemników z klockami tak aby w każdym pojemniku był co najwyżej jeden klocek każdego koloru.
	Klocki przekładamy po jednym w każdym ruchu i tylko do sąsiedniego pojemnika lub pomiędzy pierwszym a ostatnim pojemnikiem. (pojemniki tworzą pierścień)

Dane:
	n ponumerowanych kolejno pojemników 
	k różnych kolorów klocków
	pi pojemność itego pojemnika (zakładamy że pojemność jest wyrażona w ilości klocków tj. p=10 oznacza że może tam być maksymalnie 10 klocków)
	nie więcej niż n klocków każdego koloru we wszystkich n pojemnikach co implikuje że postawiony problem zawsze da się rozwiązać (nie ma sytuacji że klocków jednego koloru jest więcej niż pojemników)


Warunek konieczny i wystarczający:
	w każdym pojemniku jest co najwyżej jeden klocek każdego koloru.

Planuję napisać parametryzowalny i losowy generator danych wejściowych z sensownymi ograniczeniami. (żeby wszystko mieściło się w ramie) 
Dane wejściowe np. parametry generowania środowiska i sposób jego rozwiązania planuję przekazywać jako opcje wywołania programu w terminalu. 
Ewentualnie jeżeli jest taka konieczność, przewiduję przekazywanie pliku tekstowego z całą strukturą środowiska (pojemnikami  i klockami)

Struktury danych:
	Klasa klocek; atrybut: kolor
	Klasa pojemnik; atrybut: kolekcja klocków
	Singleton reprezentujący środowisko; atrybut: kolekcja pudełek; metody związane z interfejsem czyli przenoszenie klocków między pudełkami

Proponowane algorytmy:
	(Chyba) naiwne: iterowanie po wszystkich pojemnikach; dla każdego pojemnika sprawdzamy czy jest tam więcej niż jeden klocek tego samego koloru, jeżeli tak to przenosimy wszystkie nadmiarowe do kolejnego pojemnika. Jeżeli nie, przechodzimy do sprawdzania pojemnika obok.
	Mniej naiwne:
		
