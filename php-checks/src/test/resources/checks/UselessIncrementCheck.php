<?php

$a = 42;

function pickNumber() {
  $i = 0;
  $j = 0;

  $i = $i++; // Noncompliant {{Remove this increment or correct the code not to waste it.}}
//     ^^^^
  $j = $j--; // Noncompliant {{Remove this decrement or correct the code not to waste it.}}
//     ^^^^

  $j += $j++; // Compliant
  $i = ++$i;  // Compliant
  $j = $i++;  // Compliant

  return $j--; // Compliant: FN: should check for scope of variables
}

function other() {
  for ($i = 1; $i <= 10; $i++) { // Compliant
    echo $i++;
  }
  return $a++; // Compliant: $a is defined in outer scope
}
