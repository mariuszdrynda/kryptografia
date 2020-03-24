from functools import reduce
import math
from random import randint

class prng_lcg:
    def __init__(self, seed, m, c, n):
        self.state = seed  # ziarno
        self.m = m # mnożnik
        self.c = c
        self.n = n # modulo
    def next(self): # funkcja generuje kolejną pseudolową liczbę z zakresu [0, n]
        self.state = (self.state * self.m + self.c) % self.n
        return self.state

# znamy m i n, nie znamy c
def crack_unknown_increment(states, modulus, multiplier):
    increment = (states[1] - states[0]*multiplier) % modulus
    return modulus, multiplier, increment

# znamy n, nie znamy m i c
# s_2 - s_1 = s1*m - s0*m  (mod n)
# s_2 - s_1 = m*(s1 - s0)  (mod n)
# m = (s_2 - s_1)/(s_1 - s_0)  (mod n)
def crack_unknown_multiplier(states, modulus):
    multiplier = (states[2] - states[1]) * modinv(states[1] - states[0], modulus) % modulus 
    return crack_unknown_increment(states, modulus, multiplier)

# największy wspólny dzielnik
def egcd(a, b):
    if a == 0:
        return (b, 0, 1)
    else:
        g, x, y = egcd(b % a, a)
        return (g, y - (b // a) * x, x)

# modular division - znajduje liczbę c, taką, że (b * c) % n = a % n
def modinv(b, n):
    g, x, _ = egcd(b, n)
    return x % n

# nie znamy niczego
# mając kilka wielokrotnosci losowej liczby istnieje duże prawopodobieństwo,
# że nwd tych wielokrotności = szukanej liczbie
def crack_unknown_modulus(states):
    # lista róźnic kolejnych generowanych liczb
    diffs = [s1 - s0 for s0, s1 in zip(states, states[1:])] 
    zeroes = [t2*t0 - t1*t1 for t0, t1, t2 in zip(diffs, diffs[1:], diffs[2:])]
    modulus = abs(reduce(math.gcd, zeroes))
    return crack_unknown_multiplier(states, modulus)

# UWAGA! Python3 niepoprawnie interpretuje duże liczby zaokrąglając końcówki
def test():
    nosucc = 0
    # Liczba pierwsza, (x_1 - x_0) * y mod m = 1, dla dowolnego y < m .
    modulus = 1000000007
    gen = prng_lcg(randint(1,modulus), randint(1,modulus), randint(1,modulus), modulus)
    listGenVal = list()
    for x in range(0,10):
            listGenVal.append(gen.next())
    print(listGenVal)
    for i in range(0, 10):
            s = gen.next()
            n, m, c = crack_unknown_modulus(listGenVal)
            print("przewidziane\t", m, "\t", c, n, "\t")
            print("rzeczywiste\t", gen.m, "\t", gen.c, gen.n, "\t")
            gen2 = prng_lcg(s, m, c, n)
            # sprawdz czy nastepna liczba przewidywana przez cracka
            # jest tą samą co generowaną przez lcg
            if gen.next() == gen2.next(): nosucc+=1
    print("nosucc", nosucc)

test()