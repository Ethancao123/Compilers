# 
#   Adds 2 and 3 together and prints the output
#   @author Ethan Cao
#   @version 11/10/21
#

    .text 0x00400000
    .globl main
main:
    li $t0, 2 # load 2 into $t0
    li $t1, 3 # load 3 into $t1
    addu $a0, $t0, $t1 # store $t0 + $t1 in $t2
    li $v0, 1
    syscall
    li $v0, 10 # normal termination
    syscall
