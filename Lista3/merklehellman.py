import numpy as np
from random import randint

class MHK:
    def __init__(self):
        self.n = 8 #długość wektorów a i a_prim
        self.m = randint(2**201+1,2**202-1)
        w_prim = randint(2, self.m-2)
        gcd, _, _ = egcd(w_prim, self.m)
        self.w = w_prim//gcd 
        self.w_1 = modinv(self.w, self.m) #w^{-1}
        self.a_prim = [0]*self.n 
        for i in range(1, self.n+1):
            self.a_prim[i-1] = randint(2**100*(2**(i-1)-1)+1, 2**(i-1)*2**100)
        self.a = np.array([self.w*a%self.m for a in self.a_prim]) #klucz publiczny
        print(self.m, self.w, self.w_1, self.a, self.a_prim)
    def decrypt(self, encrypted_message):
        S = encrypted_message*self.w_1%self.m
        decrypted = [0]*self.n
        i = self.n-1
        while i>=0:
            if S >= self.a_prim[i]:
                S -= self.a_prim[i]
                decrypted[i] = 1
            i -= 1
        print(decrypted)
        return chr(int(''.join(map(str, decrypted)), 2))

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

def send_message(message, public_key):
    #message - char
    #public_key - np.array
    list_of_bin = np.array([int(x) for x in bin(ord(message))[2:]])
    while len(list_of_bin) < 8:
        list_of_bin = np.append(0, list_of_bin)
    print("list_of_bin", list_of_bin)
    return np.sum(list_of_bin.dot(public_key))

def main():
    mhk = MHK()
    encrypted_message = send_message('a', mhk.a)
    print("ENCRYPTED:", encrypted_message)
    dec = mhk.decrypt(encrypted_message)
    print("DECRYPTED:", dec)

main()