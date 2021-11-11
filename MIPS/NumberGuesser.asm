# 
#   Generates a random number and has the user guess it while returning feedback
#   @author Ethan Cao
#   @version 11/10/21
#

    .data   
        prompt: .asciiz "Guess the number between 0 and 100\n"
        msgHigh: .asciiz "That number is too high, try again \n"
        msgLow: .asciiz "That number is too low, try again \n"
        msgEnd: .asciiz "Thats the correct number!"
    .text 0x00400000
    .globl main

main:

    #generate the number and move to t0
    li $a1, 100
    li $v0, 42
    syscall
    move $t0, $a0

    #prompt the user and store the guess
    li $v0, 4
    la $a0, prompt
    syscall 
input:
    li $v0, 5
    syscall
    move $t1, $v0
    blt $t1, $t0, low
    bgt $t1, $t0, high
    j end

    #if too high
high:
    li $v0, 4
    la $a0, msgHigh
    syscall 
    j input

    #if too low
low:
    li $v0, 4
    la $a0, msgLow
    syscall 
    j input

    #end of program
end:
    li $v0, 4
    la $a0, msgEnd
    syscall
    li $v0, 10
    syscall
    